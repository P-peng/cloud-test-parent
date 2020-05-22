package com.ge.test.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 * @EnableFeignClients 开启 feign 调用
 *
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/20 0020
 */
@SpringBootApplication
@EnableFeignClients
public class OrderMain {

    private static Logger logger = LoggerFactory.getLogger(OrderMain.class);


    public static void main(String[] args) {
        SpringApplication.run(OrderMain.class);
        logger.info("##################################################");
        logger.info("###############      服务启动      ###############");
        logger.info("##################################################");
    }

}
