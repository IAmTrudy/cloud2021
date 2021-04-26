package com.lxp.springcloud.controller;

import com.lxp.springcloud.entities.CommonResult;
import com.lxp.springcloud.entities.Payment;
import com.lxp.springcloud.lb.MyLB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class OrderController {

//    public static final String URL = "http://localhost:8001";
    private static final String URL = "http://CLOUD-PAYMENT-SERVER";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

//    @Autowired
//    private MyLB myLB;

    @GetMapping("/consumer/payment/creat")
    public CommonResult<Payment> creat(Payment payment){
        return restTemplate.postForObject(URL+"/payment/creat",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }

//    @GetMapping(value = "/consumer/lb")
//    public String getLB(){
//        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVER");
//        if(instances == null || instances.size() <=0){
//            return "无服务";
//        }else{
//            ServiceInstance instance = myLB.instance(instances);
//            return instance.getPort()+"";
//        }
//    }

    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        String s = restTemplate.getForObject(URL+"/payment/zipkin",String.class);
        return s;
    }
}
