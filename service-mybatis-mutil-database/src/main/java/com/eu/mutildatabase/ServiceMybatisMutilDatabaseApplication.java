package com.eu.mutildatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceMybatisMutilDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMybatisMutilDatabaseApplication.class, args);
    }

}

