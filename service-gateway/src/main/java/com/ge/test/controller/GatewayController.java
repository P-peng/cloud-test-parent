package com.ge.test.controller;

import com.ge.test.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
    @Autowired
    private GatewayService gatewayService;

    @GetMapping("fush")
    public Object fush(){
        return gatewayService.loadAllRoute();
    }
}
