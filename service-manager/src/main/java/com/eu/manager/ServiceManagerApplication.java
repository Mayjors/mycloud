package com.eu.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.eu.manager.dao")
public class ServiceManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceManagerApplication.class, args);
    }
}
