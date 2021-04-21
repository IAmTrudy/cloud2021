package com.lxp.springcloud.controller;

import com.lxp.springcloud.entities.CommonResult;
import com.lxp.springcloud.entities.Payment;
import com.lxp.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;


    @PostMapping(value = "/payment/creat")
    public CommonResult creat(@RequestBody Payment payment){
        int result = paymentService.creat(payment);
        log.info("******插入结果："+result);

        if(result>0){
            return new CommonResult(200,"插入数据库成功,serverPort:"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("******插入结果"+payment);

        if(payment != null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else {
            return new CommonResult(444,"查询失败",null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service:"+service);//service:cloud-payment-server
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                String serviceId = instance.getServiceId();
                String host = instance.getHost();
                int port = instance.getPort();
                String instanceId = instance.getInstanceId();
                URI uri = instance.getUri();
                String scheme = instance.getScheme();
                log.info("serviceId:"+serviceId);//serviceId:CLOUD-PAYMENT-SERVER
                log.info("host:"+host);//host:192.168.43.21
                log.info("port:"+port);//port:8001
                log.info("scheme:"+scheme);//scheme:http
                log.info("uri:"+uri);//uri:http://192.168.43.21:8001
            }
        }

        return discoveryClient;
    }

    @GetMapping("/payment/timeout")
    public String timeout(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
