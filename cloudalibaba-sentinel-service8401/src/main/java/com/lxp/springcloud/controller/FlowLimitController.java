package com.lxp.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        System.out.println(new Date());
        return "testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "testB";
    }

}
