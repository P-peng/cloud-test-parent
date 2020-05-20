package com.ge.test.service.api.member.controller;

import com.ge.test.service.api.member.impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/20 0020
 */
@RestController
public class MemberController {

    @Autowired
    MemberServiceImpl memberService;

    @GetMapping("/get")
    public Object get(){
        return memberService.getUser(2);
    }
}
