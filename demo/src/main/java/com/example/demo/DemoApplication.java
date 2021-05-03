package com.example.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("kr.or.connect.reservation.core.domain")
@EnableBatchProcessing
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws Exception {
        System.exit(SpringApplication.exit(SpringApplication.run(DemoApplication.class, args)));

//        SpringApplication.run(DemoApplication.class, args);
    }

}
