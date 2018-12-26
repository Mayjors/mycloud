package com.eu.manager.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author yuanjie
 * @date 2018/12/24 16:06
 */
@Component
public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("---init----");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("before...");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("after...");
    }

    @Override
    public void destroy() {
        System.out.println("---destroy----");
    }
}
