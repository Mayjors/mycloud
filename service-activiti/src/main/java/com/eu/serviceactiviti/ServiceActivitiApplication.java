package com.eu.serviceactiviti;

import org.activiti.spring.boot.SecurityAutoConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ServiceActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceActivitiApplication.class, args);
    }

}
