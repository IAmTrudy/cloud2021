package com.lxp.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lxp.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircleBreakerController {

    public static final String URL = "http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback",
            fallback = "handlerFallback",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult fallback(@PathVariable("id") Long id){
        CommonResult result = restTemplate.getForObject(URL + "/payment/get/" + id, CommonResult.class);
        if(id == 4){
            throw new IllegalArgumentException("参数异常");
        }else if(result.getData() == null){
            throw  new NullPointerException("空指针异常");
        }
        return result;
    }

    public CommonResult handlerFallback(@PathVariable("id") Long id,Throwable e){
        return new CommonResult(444,"fallback异常处理"+e.getMessage(),null);
    }
}
