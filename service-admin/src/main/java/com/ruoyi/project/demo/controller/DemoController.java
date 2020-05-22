package com.ruoyi.project.demo.controller;

import com.ge.test.service.api.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/21 0021
 */
@RestController
@RequestMapping("/demo/demo")
public class DemoController {

    @Resource
    MemberService memberService;

    @GetMapping("/test")
    public Object test(){
        return memberService.getUser(2);
    }
}
