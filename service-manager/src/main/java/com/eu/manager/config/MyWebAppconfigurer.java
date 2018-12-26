package com.eu.manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yuanjie
 * @date 2018/12/24 16:42
 */
@Configuration
public class MyWebAppconfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，连接以/test/为前缀的url路径
        registry.addInterceptor(new TestHandlerInterceptor()).addPathPatterns("/test/**");
    }

}
