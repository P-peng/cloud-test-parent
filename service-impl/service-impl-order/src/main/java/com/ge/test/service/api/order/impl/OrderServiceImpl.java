package com.ge.test.service.api.order.impl;

import com.ge.test.service.api.member.MemberService;
import com.ge.test.service.api.member.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/20 0020
 */
@RestController
public class OrderServiceImpl {

    @Autowired
    MemberService memberService;
//
    @RequestMapping("/test")
    public Object test(){
        return memberService.getUser(1);
    }
//
    @RequestMapping("/test2")
    public Object test2(){
        UserDto dto = new UserDto();
        dto.setId(66);
        dto.setName("123");
        dto = memberService.getUser2(dto);
        return memberService.getUser2(dto);
    }
}
