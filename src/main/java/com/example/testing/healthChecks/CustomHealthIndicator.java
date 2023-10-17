package com.example.testing.healthChecks;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        //represent a flag that may sth validating somewhere
        boolean error = true;
        if(error){
            return Health.down().withDetail("error key", 123).build();
        }
        return Health.up().build();
    }
}
