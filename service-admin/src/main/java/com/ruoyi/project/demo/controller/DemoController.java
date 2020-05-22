package com.ruoyi.project.demo.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.ge.test.service.api.member.MemberService;
import com.ge.test.service.api.member.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

//    @NacosInjected
//    private NamingService namingService;

    @GetMapping("/test")
    public Object test(){
        String str = memberService.getUser(2);
        UserDto userDto = new UserDto();
        userDto.setId(99);
        userDto = memberService.getUser2(userDto);
        return userDto;
    }
}
