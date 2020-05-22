package com.ge.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/22 0022
 */
@SpringBootApplication
public class ChainMain {
    private static Logger logger = LoggerFactory.getLogger(ChainMain.class);

    public static void main(String[] args) {
        SpringApplication.run(ChainMain.class);
        logger.info("##################################################");
        logger.info("###############      服务启动      ###############");
        logger.info("##################################################");
    }
}
