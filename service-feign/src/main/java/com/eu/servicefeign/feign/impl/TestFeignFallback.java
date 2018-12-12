package com.eu.servicefeign.feign.impl;

import com.eu.servicefeign.feign.TestFeign;
import org.springframework.stereotype.Component;

/**
 * @author yuanjie
 * @date 2018/12/12 10:08
 */
@Component
public class TestFeignFallback implements TestFeign {
    @Override
    public String info() {
        return "调用Feign服务超时";
    }
}
