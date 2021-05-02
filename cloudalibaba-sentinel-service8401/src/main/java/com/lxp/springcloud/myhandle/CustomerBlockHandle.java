package com.lxp.springcloud.myhandle;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lxp.springcloud.entities.CommonResult;

public class CustomerBlockHandle {
    public static CommonResult handleException(BlockException e){
        return new CommonResult(444,"自定义失败");
    }
}
