package com.eu.eurekaclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuanjie
 * @date 2018/12/11 17:49
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class MyController {

    @Autowired
    private Registration registration;  // 服务注册
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/info" ,method = RequestMethod.GET)
    public String info() {
        List<ServiceInstance> instances = client.getInstances(registration.getServiceId());
        if (instances != null && instances.size()>0) {
            log.info("/hello,host:" + instances.get(0).getHost()+", service_id:"+instances.get(0).getServiceId());
        }
        return "hello I am is spring-serviceA"; //测试代码直接返回一个字符串，不再调用service层等等。
    }
}
