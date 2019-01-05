package com.eu.web.support.config;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author yuanjie
 * @date 2019/1/4 17:02
 */
public class LocaleResolverInterceptor implements HandlerInterceptor {
    private static ThreadLocal<Locale> langLocaleSet = new ThreadLocal<Locale>();
    private static ThreadLocal<Locale> formatLocaleSet = new ThreadLocal<Locale>();

    public static Locale getLangLocale() {
        return langLocaleSet.get();
    }

    public static Locale getFormatLocale() {
        return formatLocaleSet.get();
    }

    @Resource
    private SystemConfigProperties systemConfigProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Locale  locale = getLocale(request);
        langLocaleSet.set(locale);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            /*@Nullable */Exception ex) throws Exception {
        langLocaleSet.remove();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    public Locale getLocale(HttpServletRequest request) {
        //1. 取固定的配置
//		Locale locale = null;
        String fl = "fixedLocale";
        if(StringUtils.isNotEmpty(fl)) {
            return getLocale(fl);
        }
        //根据请求参数来获取——PDA请求
        String rl = request.getParameter(systemConfigProperties.getLocaleRequestParameterName());
        if(StringUtils.isNotEmpty(rl)) {
            return getLocale(rl);
        }
        // 2. cookie 支持
//		Cookie[] cookies = request.getCookies();
        String lang = "";
        Cookie cookie = WebUtils.getCookie(request, FpxLocaleConstants.PREFERENCE_LANGUAGE);
        if(cookie != null ){
            lang = cookie.getValue();
            if(StringUtils.isNotEmpty(lang)) {
                return getLocale(lang);
            }
        }
        //3. 取浏览器中的设置
//		request.setAttribute(FpxLocaleConstants.SYSTEM_LOCALE_ATTRIBUTE, request.getLocale());
        Locale locale = request.getLocale(); //即使不给定accept-language 请求头，也会返回一个默认值
        return locale;
    }

    private Locale getLocale(String lang) {
        return LocaleUtils.toLocale(lang.replace("-", "_"));
    }
}
