package com.eu.serviceflowable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceFlowableApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFlowableApplication.class, args);
    }

}
