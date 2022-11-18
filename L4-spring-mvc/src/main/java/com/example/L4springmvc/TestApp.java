package com.example.L4springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class TestApp {

    public static void main(String[] args) {
//        SpringApplication springApplication = new SpringApplication(TestApp.class);
//        springApplication
//                .setDefaultProperties(
//                        Collections.singletonMap("server.port", "8082"));
//        springApplication.run(args);

        SpringApplication.run(TestApp.class, args);
    }
}
