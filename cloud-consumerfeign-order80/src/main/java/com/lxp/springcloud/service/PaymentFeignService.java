package com.lxp.springcloud.service;

import com.lxp.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVER")//调用服务的名称
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")//调用服务的controller地址
    CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout")
    String timeout();
}
