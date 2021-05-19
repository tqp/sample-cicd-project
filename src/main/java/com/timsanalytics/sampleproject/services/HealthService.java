package com.timsanalytics.sampleproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class HealthService {
    private final Environment environment;

    @Autowired
    public HealthService(Environment environment) {
        this.environment = environment;
    }

    public String getBuildTimestamp() {
        return this.environment.getProperty("application.build.timestamp");
    }
}
