package com.example.bucket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BucketApplication {

    public static void main(String[] args) {
        SpringApplication.run(BucketApplication.class, args);
    }

}
