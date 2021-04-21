package com.lxp.springcloud.controller;

import com.lxp.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/hystrix/ok/{id}")
    @HystrixCommand
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String s = paymentHystrixService.paymentInfo_OK(id);
        return s;
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="1000")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id){
        String s = paymentHystrixService.paymentInfo_TimeOut(id);
        return s;
    }


    public String paymentTimeOutFallbackMethod(Integer id){
        return "消费者80的FallBack";
    }

    public String payment_Global_FallbackMethod(){
        return "Global：消费者80的FallBack";
    }

}
