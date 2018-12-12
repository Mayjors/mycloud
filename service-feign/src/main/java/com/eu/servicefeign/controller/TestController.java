package com.eu.servicefeign.controller;

import com.eu.servicefeign.feign.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanjie
 * @date 2018/12/12 9:50
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestFeign testFeign;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Object getInfo() {
        String info =  testFeign.info();
        return info;
    }

}
