package com.eu.servicespark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ServiceSparkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSparkApplication.class, args);
    }
}
