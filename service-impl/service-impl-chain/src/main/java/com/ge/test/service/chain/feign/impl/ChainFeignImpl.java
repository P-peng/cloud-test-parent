package com.ge.test.service.chain.feign.impl;

import com.ge.test.api.chain.feign.ChainFeign;
import com.ge.test.service.chain.client.CAClient;
import com.ge.test.service.chain.client.ChannelClient;
import com.ge.test.service.chain.client.FabricClient;
import com.ge.test.service.chain.config.FabricConfig;
import com.ge.test.service.chain.config.FabricConfigBean;
import com.ge.test.service.chain.enumeration.FabricNodeEnum;
import com.ge.test.service.chain.service.ChainService;
import com.ge.test.service.chain.user.UserContext;
import com.ge.test.service.chain.util.Util;
import org.apache.commons.io.IOUtils;
import org.hyperledger.fabric.sdk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/22 0022
 */
@RestController
@Service
public class ChainFeignImpl implements ChainFeign {
    @Autowired
    private ChainService chainService;
    @Resource
    FabricConfigBean fabricConfigBean;

    @Override

    public String getChain(Integer integer) {
        return "chain";
    }

    @Override
    public String getByKey(String s) {
        return chainService.invoke("001", "123", "!23");
    }


}
