package com.eu.serviceactuator.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author yuanjie
 * @date 2019/1/26 16:07
 */
@Component("my1")
public class MyHealthIndicator implements HealthIndicator {
    private static final String VERSION = "v1.0.0";

    @Override
    public Health health() {
        int code = 0;
        if (code != 0) {
            return Health.down().withDetail("code", code).withDetail("version", VERSION).build();
        }
        return Health.up().withDetail("code", code).withDetail("version", VERSION).build();
    }
}
