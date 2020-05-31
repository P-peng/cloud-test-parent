package com.ge.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ge.test.mapper")
public class GatewayMain {

    public static void main(String[] args) {
        SpringApplication.run(GatewayMain.class);
    }
}
