package com.lxp.springcloud.service.impl;

import com.lxp.springcloud.entities.CommonResult;
import com.lxp.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult payment(Long id) {
        return new CommonResult(444,"服务降级openfeign",null);
    }
}
