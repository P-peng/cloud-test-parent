package com.ge.test.service.api.member.controller;

import com.ge.test.api.chain.feign.ChainFeign;
import com.ge.test.service.api.member.impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/20 0020
 */
@RestController
@RefreshScope
public class MemberController {

    @Autowired
    MemberServiceImpl memberService;
    @Autowired
    ChainFeign chainFeign;
//
    @Value("${test.name}")
    private String configName;

    @GetMapping("/get")
    public Object get(){
        return memberService.getUser(2);
    }

    @GetMapping("/getConfig")
    public Object getConfig(){
        return configName;
    }

    @GetMapping("/getChain")
    public Object getChain(){
        return chainFeign.getChain(1);
    }
}
