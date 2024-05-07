package com.kepler.dabaicai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.kepler"})
public class DabaicaiApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DabaicaiApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DabaicaiApplication.class);
    }
}
