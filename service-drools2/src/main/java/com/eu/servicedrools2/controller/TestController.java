package com.eu.servicedrools2.controller;

import com.eu.servicedrools2.service.KieFileSystemTest;
import com.eu.servicedrools2.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanjie
 * @date 2019/3/18 20:41
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test() {
        testService.test();
        return "x";
    }

    @Autowired
    private KieFileSystemTest kieFileSystemTest;

    @GetMapping("/kie")
    public String t() {
        kieFileSystemTest.handle();
        return "s";
    }

}
