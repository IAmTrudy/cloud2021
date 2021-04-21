package com.lxp.springcloud.service;

import com.lxp.springcloud.entities.Payment;

public interface PaymentService {
    /**
     *
     * @param payment
     * @return
     */
    public int creat(Payment payment);

    /**
     *
     * @param id
     * @return
     */
    public Payment getPaymentById(Long id);
}
