package com.eu.serviceredisson.controller;

import com.eu.serviceredisson.config.DistributedRedisLock;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanjie
 * @date 2019/1/31 15:18
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/redder")
    public String redder() {
        String key = "test123";
        // 加锁
        DistributedRedisLock.acquire(key);
        // 执行具体业务逻辑

        // 释放锁
        DistributedRedisLock.release(key);
        return "sds";
    }
}
