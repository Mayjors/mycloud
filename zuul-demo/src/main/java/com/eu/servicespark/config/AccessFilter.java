package com.eu.servicespark.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 服务过滤器
 * 实现请求被路由之前检查请求中是否有accessToken参数，有就进行路由，没有就拒绝访问
 * @author yuanjie
 * @date 2018/12/11 18:22
 */
public class AccessFilter extends ZuulFilter {
    /**
     * pre: 可以在请求被路由之前调用
     * route: 在路由请求时候被调用
     * post: 在route和error过滤器之后被调用
     * error: 处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        // 优先级为0，数字越大，优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 是否执行该过滤器，此处为true，说明需要过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println(String.format("%s demoFilter request to $s", request.getMethod(), request.getRequestURL().toString()));
        String username = request.getParameter("username"); // 获取请求的参数
        if (!StringUtils.isEmpty(username) && username.equals("lilei")) {
            ctx.setSendZuulResponse(true);  // 对该请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true); // 设值，让下一个Filter看到上一个Filter的状态
            return null;
        } else {
            ctx.setSendZuulResponse(false);  // 过滤该请求，不对该请求进行路由
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"result\":\"username is not correct!\"}");// 返回错误内容
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
