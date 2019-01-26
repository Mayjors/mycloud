package com.eu.serviceactuator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanjie
 * @date 2019/1/26 15:50
 */
@RestController
@RequestMapping("/act")
public class TestController {

    @RequestMapping("test")
    public String test() {
        System.out.println("xxx");
        return "sb";
    }
}
