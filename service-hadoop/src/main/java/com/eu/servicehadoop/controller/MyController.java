package com.eu.servicespark.controller;

import com.eu.servicespark.service.SparkTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @Autowired
    private SparkTestService sparkTestService;

    @RequestMapping("/demo/top10")
    public Map<String,Object> calculateTopTen() {
        return sparkTestService.calculateTopTen();
    }

    @RequestMapping("/demo/exercise")
    public void exercise() {
        sparkTestService.sparkExerciseDemo();
    }

    @RequestMapping("/demo/stream")
    public void streamingDemo() throws InterruptedException {
        sparkTestService.sparkStreaming();
    }
}
