package com.example.springredisson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringRedissonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRedissonApplication.class, args);
    }

}
