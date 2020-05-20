package com.ge.test.service.api.member.impl;

import com.ge.test.service.api.member.MemberService;
import com.ge.test.service.api.member.biz.MemberBiz;
import com.ge.test.service.api.member.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/20 0020
 */
@RestController
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberBiz memberBiz;

    public String getUser(Integer userId) {
        memberBiz.get();
        return "123213";
    }

    public UserDto getUser2(UserDto userDto) {
        userDto.setName("6666");
        return userDto;
    }

}
