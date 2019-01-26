package com.eu.serviceactuator.config;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 自制端点
 * @author yuanjie
 * @date 2019/1/26 17:32
 */
@Configuration
@Endpoint(id = "david")
public class MyEndPoint {
    @ReadOperation
    public Map<String, String> test() {
        Map<String, String> result = new HashMap<>();
        result.put("name", "david");
        result.put("age", "18");
        return result;
    }
}
