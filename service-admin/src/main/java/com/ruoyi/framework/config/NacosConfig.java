package com.ruoyi.framework.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/21 0021
 */
@Configuration
public class NacosConfig {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${nacos.discovery.server-addr}")
    private String nacosDiscoveryServerAddr;
    @Value("${nacos.discovery.port}")
    private Integer port;
    @NacosInjected
    private NamingService namingService;
    @PostConstruct
    public void registerInstance() throws NacosException {
        namingService.registerInstance(applicationName, nacosDiscoveryServerAddr, port);
    }
}
