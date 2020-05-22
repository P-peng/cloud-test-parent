package com.ge.test.service.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/20 0020
 */
@SpringBootApplication
public class MemberMain {
    private static Logger logger = LoggerFactory.getLogger(MemberMain.class);

    public static void main(String[] args) {
        SpringApplication.run(MemberMain.class);
        logger.info("##################################################");
        logger.info("###############      服务启动      ###############");
        logger.info("##################################################");
    }
}
