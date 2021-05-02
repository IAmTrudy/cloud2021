package com.lxp.springcloud.controller;

import com.lxp.springcloud.entities.CommonResult;
import com.lxp.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/{id}")
    public CommonResult payment(@PathVariable("id")Long id){
        CommonResult result = paymentService.payment(id);
        return result;
    }
}
