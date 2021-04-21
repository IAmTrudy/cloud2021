package com.lxp.springcloud.service;

public interface PaymentService {

    String paymentInfo_Ok(Integer id);
    String paymentInfo_TimeOut(Integer id);
    String paymentInfo_TimeOutHandler(Integer id);
    String paymentCircuitBreaker(Integer id);
}
