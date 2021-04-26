package com.lxp.springcloud;


//import com.lxp.rule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//标识访问某个服务的时候使用特定的负载均衡规则
//@RibbonClient(name = "CLOUD-PAYMENT-SERVER",configuration = MySelfRule.class)
//@EnableDiscoveryClient
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
