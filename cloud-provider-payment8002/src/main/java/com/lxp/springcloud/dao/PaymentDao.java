package com.lxp.springcloud.dao;

import com.lxp.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
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
    public Payment getPaymentById(@Param("id") Long id);
}
