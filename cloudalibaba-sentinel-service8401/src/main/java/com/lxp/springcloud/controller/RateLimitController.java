package com.lxp.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lxp.springcloud.entities.CommonResult;
import com.lxp.springcloud.entities.Payment;
import com.lxp.springcloud.myhandle.CustomerBlockHandle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名限流OK",new Payment(2020L,"serial001"));
    }

    public CommonResult handleException(BlockException e){
        return new CommonResult(444,e.getClass().getCanonicalName()+" 服务不可用");
    }

    @GetMapping("/rateLimit/customerBlockHandle")
    @SentinelResource(value = "customerBlockHandle",
            blockHandlerClass = CustomerBlockHandle.class,
            blockHandler = "handleException")
    public CommonResult customerBlockHandle(){
        return new CommonResult(200,"自定义",new Payment(2020L,"serial003"));
    }

}
