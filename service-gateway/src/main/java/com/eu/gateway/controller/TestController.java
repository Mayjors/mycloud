package com.eu.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanjie
 * @date 2019/1/18 14:58
 */
@RestController
@RequestMapping("/user")
public class TestController {

    @RequestMapping(value = "/xx", method = RequestMethod.GET)
    public String xx() {
        System.out.println("---xxx---");
        return "xx";
    }
}
