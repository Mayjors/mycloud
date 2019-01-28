package com.eu.serviceactuator.controller;

import com.eu.servicestarter.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanjie
 * @date 2019/1/26 15:50
 */
@RestController
@RequestMapping("/act")
public class TestController {

    @Autowired
    private ExampleService exampleService;

    @RequestMapping("test")
    public String test() {
        System.out.println("xxx");
        return "sb";
    }

    @RequestMapping("/example")
    public String example() {
        String word = "word";
        return exampleService.wrap(word);
    }
}
