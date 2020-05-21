package com.ge.test.service.api.member;

import com.ge.test.service.api.member.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/20 0020
 */
@FeignClient(value = "my-member")
@RequestMapping("/user")
public interface MemberService {

    @GetMapping("/getUser")
    String getUser(@RequestParam(value = "userId", required = false) Integer userId);

    @PostMapping("/getUser2")
    UserDto getUser2(@RequestBody() UserDto userDto);
}
