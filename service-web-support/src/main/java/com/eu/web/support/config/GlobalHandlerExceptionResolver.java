package com.eu.web.support.config;

import com.alibaba.fastjson.JSON;
import com.eu.util.exception.BusinessRuntimeException;
import com.eu.util.result.ResultMessage;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.ShiroException;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

/**
 * 全局异常处理器
 * @author yuanjie
 * @date 2019/1/4 16:55
 */
@Slf4j
public class GlobalHandlerExceptionResolver extends DefaultHandlerExceptionResolver implements Ordered {

    @Override
    public int getOrder() {
        return 100;
    }

    @Resource
    private MessageSource messageSource;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("当前失败的URI：{}，请求参数：{}，method：{}, handler：{}", request.getRequestURI(), request.getParameterMap(), request.getMethod(), handler);
        log.error(ex.getMessage(), ex);

        Locale locale = LocaleResolverInterceptor.getLangLocale();
        log.info("request Locale:{}", locale);
        try {
            if (ex instanceof DataAccessException) {
                return handleUnCheckException(ex,request,response,handler,"err100006",locale);
            } else if (ex instanceof FeignException || ex instanceof HystrixRuntimeException) {
                Throwable nowe = ex;
                if (ex instanceof HystrixRuntimeException) {
                    nowe = ex.getCause();
                } else {
                    nowe = ex.getCause();
                    if (nowe instanceof BusinessRuntimeException) {
                        BusinessRuntimeException bre = (BusinessRuntimeException) nowe;
                        log.info("xxxxxxxxxxxxxx.....errcode: {}", bre.getErrCode());
                        return handleInternalError(bre,request,response,handler,locale);
                    }
                }return handleFeignException(nowe,request,response,handler,locale);
            }
            else if(ex instanceof BusinessRuntimeException){
                return handleInternalError((BusinessRuntimeException)ex,request,response,handler,locale);
            }
            else if(ex instanceof ShiroException) {
                return handleShiroException((ShiroException)ex,request,response,handler,locale);
            }
            else if(ex instanceof RuntimeException){
                return handleUnCheckException(ex,request,response,handler,"err100005",locale);
            }
            //TODO 增加其它异常处理
        }catch(Exception e){
            log.error("Spring MVC处理异常再次发生异常！",e);
        }
        return super.doResolveException(request, response, handler, ex);
    }

    private ModelAndView handleUnCheckException(Throwable ex, HttpServletRequest request, HttpServletResponse response,
                                                Object handler,String code,Locale locale) throws IOException {
        ResultMessage m = new ResultMessage();
        m.setCode(code);
        String i18nmessage = messageSource.getMessage(m.getCode(), new Object[]{ex.getCause()}, locale);
        m.setMessage(i18nmessage);
        m.setArgs(ex.getMessage() + Arrays.toString(ex.getStackTrace()));
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(m));
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.flushBuffer();
        return new ModelAndView();  // 这里返回了对象后， 不会再去系统中找全局的500错误页面
    }

    private ModelAndView handleFeignException(Throwable ex,
                                              HttpServletRequest request, HttpServletResponse response, Object handler,Locale locale) throws IOException {
        ResultMessage m = new ResultMessage();
        Object[] args = null;
        String errcode = "err100004";
        if(ex  instanceof BusinessRuntimeException) {
            String berrcode = ((BusinessRuntimeException) ex).getErrCode();
            if(StringUtils.isNotEmpty(berrcode)) {
                errcode = berrcode;
                args = ((BusinessRuntimeException) ex).getParams();
            }
        }
        m.setCode(errcode);
        String i18nmessage = messageSource.getMessage(m.getCode(), args,/*request.getLocale()*/locale);
        m.setMessage(i18nmessage);
        m.setValue(ex);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(m));
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.flushBuffer();
        return new ModelAndView();//这里返回了对象后，不会再去系统中找全局的500错误页面
    }

    private ModelAndView handleInternalError(BusinessRuntimeException ex, HttpServletRequest request, HttpServletResponse response,
                                            Object handler, Locale locale) throws IOException {
        ResultMessage m = new ResultMessage();
        String errCode = ex.getErrCode();
        String status = "";
        if (StringUtils.isNotBlank(errCode) && errCode.indexOf("-")>0) {
            String [] scode = errCode.split("-");
            if (scode.length == 2) {
                status = scode[0];
                errCode = scode[1];
            }
        }
        if (StringUtils.isBlank(errCode)) {
            log.warn("######请在代码中补充错误提示代码！message:{} ######", ex.getMessage());
            errCode = "err100001";
        }
        m.setCode(errCode);
        String i18nmessage = messageSource.getMessage(errCode, ex.getParams(), locale);
        if (StringUtils.isNotBlank(i18nmessage) && !i18nmessage.equals(errCode)) {
            m.setMessage(i18nmessage);
        } else {
            log.warn("######请在资源配置文件中补充错误提示信息！code: {} #######", errCode);
            m.setMessage("请在资源文件中配置错误提示信息！！！");
        }
        HttpStatus httpStatus = HttpStatus.OK;
        if (StringUtils.isNotBlank(status)) {
            if(HttpResultTypeEnum.validate.toString().equals(status)){
                httpStatus = HttpStatus.PRECONDITION_FAILED;
            }
            else if(HttpResultTypeEnum.unauthorized.toString().equals(status)){
                httpStatus = HttpStatus.UNAUTHORIZED;
            }
            else if(HttpResultTypeEnum.unexpected.toString().equals(status)){
                httpStatus = HttpStatus.EXPECTATION_FAILED;
            }
        }
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
//	        	response.getWriter().print(json);
        response.getWriter().print(JSON.toJSONString(m));
        response.setStatus(httpStatus.value());
        response.flushBuffer();
        return new ModelAndView();
    }

    private ModelAndView handleShiroException(Throwable ex,
                                              HttpServletRequest request, HttpServletResponse response, Object handler,Locale locale) throws IOException {
        ResultMessage m = new ResultMessage();
        m.setCode("err100002");
        String i18nmessage = messageSource.getMessage(m.getCode(), null,/*request.getLocale()*/locale);
        m.setMessage(i18nmessage);
        m.setValue(ex);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(m));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.flushBuffer();
        return new ModelAndView();//这里返回了对象后，不会再去系统中找全局的500错误页面
    }
}
