package com.eu.eurekaserver.config;

import com.eu.eurekaserver.utils.MD5Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuanjie
 * @date 2019/3/26 18:15
 */
@Configuration
public class MD5AutoConfiguration {
    @Bean
    MD5Service md5Service() {
        return new MD5Service();
    }
}
