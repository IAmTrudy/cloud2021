package com.lxp.springcloud.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced//负载均衡，多个服务提供者使用同一个服务名称，无法找到确定的一个来提供服务，增加负载均衡的策略，让其自己轮询找到合适的服务提供者
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
