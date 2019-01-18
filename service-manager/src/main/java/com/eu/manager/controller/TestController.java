package com.eu.manager.controller;

import com.eu.manager.mq.sender.MessageSender;
import com.eu.manager.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanjie
 * @date 2018/12/12 21:01
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MessageSender messageSender;
    @Autowired
    private TestService testService;

    @RequestMapping("/rabbit")
    public void send(String s) {
        messageSender.sendInfo(s);
    }

    @RequestMapping("/redis")
    public String o() {
        System.out.println("----------sdsdsdsd------");
        testService.test();
        return "xxx";
    }

    @RequestMapping("/xx")
    public String oo() {
        System.out.println("--------------xs-------------------");
        return "xx";
    }
}
