package com.eu.servicemybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.eu.servicemybatis.dao")
public class ServiceMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMybatisApplication.class, args);
    }
}
