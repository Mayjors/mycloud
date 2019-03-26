package com.eu.serviceapollo.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.eu.serviceapollo.utils.PropertiesUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.Set;

/**
 * @author yuanjie
 * @date 2019/3/12 14:51
 */
@RestController
@RequestMapping("/apollo")
public class ApolloDemoController {

    /**
     * 从Apollo获取配置信息
     */
    @ApolloConfig
    private Config config;

    @Value(("${userName}"))
    private String userName;

    @GetMapping("/read_demo")
    public Properties apploReadDemo() {
        /**
         * 得到当前app.id中的配置
         */
        System.out.println(userName);

        Set<String> set = config.getPropertyNames();
        for (String key: set) {
            PropertiesUtils.properties.setProperty(key,config.getProperty(key,null));
        }
        for(String key : PropertiesUtils.properties.stringPropertyNames()){
            System.out.println(key+">>>"+PropertiesUtils.properties.getProperty(key));
        }
        return PropertiesUtils.properties;
    }
}
