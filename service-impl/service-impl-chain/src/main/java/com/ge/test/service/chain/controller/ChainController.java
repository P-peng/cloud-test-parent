package com.ge.test.service.chain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/22 0022
 */
@Controller
public class ChainController {

    @GetMapping("/test")
    @ResponseBody
    public Object getTest(){
        return "test";
    }
}
