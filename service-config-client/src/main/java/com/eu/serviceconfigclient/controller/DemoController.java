package com.eu.serviceconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanjie
 * @date 2019/3/27 15:35
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Value("${demo.env}")
    private String env;

    @GetMapping("/hello")
    public String hello() {
        return "Hello! It's " + env;
    }
}
