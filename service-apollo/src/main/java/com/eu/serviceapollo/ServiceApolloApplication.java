package com.eu.serviceapollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableApolloConfig
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ServiceApolloApplication {

//    static {
//        System.setProperty("-Dapp.id", "sb");
//        System.setProperty("-Denv", "dev");
//        System.setProperty("-Ddev_meta", "http://127.0.0.1:8000");
//    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceApolloApplication.class, args);
    }

}
