package com.lxp.springcloud.service.impl;

import com.lxp.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    //服务降级自此开始
    @Override
    public String paymentInfo_Ok(Integer id) {
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK,id:"+id;
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {//fallback方法
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="3000")//超时时间
    })
    public String paymentInfo_TimeOut(Integer id) {
        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int a = 10/0;
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_TimeOut,id:"+id;
    }

    @Override
    public String paymentInfo_TimeOutHandler(Integer id){
        return "超时线程池："+Thread.currentThread().getName()+" paymentInfo_TimeOut,id:"+id;
    }

    //服务熔断自此开始
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//熔断触发的最小个数/10s
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少百分比后熔断
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000")//时间窗口期，熔断多久后尝试请求
    })
    @Override
    public String paymentCircuitBreaker(Integer id){
        if(id<0){
            throw new RuntimeException("id 不能为负");
        }
        return Thread.currentThread().getName()+"调用成功,id:"+id;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id不能为负，请重新输入,id:"+id;
    }
}
