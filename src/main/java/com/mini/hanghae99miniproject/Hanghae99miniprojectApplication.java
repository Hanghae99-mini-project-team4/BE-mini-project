package com.mini.hanghae99miniproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Hanghae99miniprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hanghae99miniprojectApplication.class, args);
    }

}
