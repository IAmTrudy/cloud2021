package com.lxp.springcloud.service;

import com.lxp.springcloud.entities.CommonResult;
import com.lxp.springcloud.service.impl.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping("/payment/get/{id}")
    CommonResult payment(@PathVariable("id")Long id);
}
