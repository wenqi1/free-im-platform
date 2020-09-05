package com.learn.freeim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.learn.freeim.dao")
public class FreeImApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeImApplication.class, args);
    }

}
