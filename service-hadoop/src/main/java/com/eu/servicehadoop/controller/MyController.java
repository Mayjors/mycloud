package com.eu.servicehadoop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanjie
 * @date 2018/12/11 17:49
 */
@RestController
@RequestMapping("/test")
public class MyController {
    @RequestMapping(value = "/info" ,method = RequestMethod.GET)
    public String info() {
        return "hello I am is spring-serviceZuul"; //测试代码直接返回一个字符串，不再调用service层等等。
    }



}
