package com.eu.serviceactuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ServiceActuatorApplication {

//    @Configuration
//    static class MyEndpointConfiguration {
//        @Bean
//        @ConditionalOnMissingBean
//        @ConditionalOnEnabledEndpoint
//        public MyEndPoint myEndPoint() {
//            return new MyEndPoint();
//        }
//    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceActuatorApplication.class, args);
    }

}

