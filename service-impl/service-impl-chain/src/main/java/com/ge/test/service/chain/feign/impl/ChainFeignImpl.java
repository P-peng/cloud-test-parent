package com.ge.test.service.chain.feign.impl;

import com.ge.test.api.chain.feign.ChainFeign;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/22 0022
 */
@RestController
@Service
public class ChainFeignImpl implements ChainFeign {
    @Override
    public String getChain(Integer integer) {
        return "chain";
    }
}
