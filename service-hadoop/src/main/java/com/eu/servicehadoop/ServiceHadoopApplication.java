package com.eu.servicehadoop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ServiceHadoopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHadoopApplication.class, args);
    }
}
