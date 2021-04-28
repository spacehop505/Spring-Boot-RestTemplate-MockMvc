package com.project.rest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.project.rest.controller", "com.project.rest.database", "com.project.rest.models"})
public class MyrestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyrestApplication.class, args);
    }

}
