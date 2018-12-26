package com.eu.manager.controller;

import com.eu.manager.mq.sender.MessageSender;
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

    @RequestMapping("/rabbit")
    public void send(String s) {
        messageSender.sendInfo(s);
    }

    @RequestMapping("/o")
    public String o() {
        System.out.println("----------sdsdsdsd------");

        return "xxx";
    }
}
