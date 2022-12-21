package com.mini.hanghae99miniproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class Hanghae99miniprojectApplication {

    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        System.out.println("현재시각 : " + new Date());
    }
    public static void main(String[] args) {
        SpringApplication.run(Hanghae99miniprojectApplication.class, args);
    }

}
