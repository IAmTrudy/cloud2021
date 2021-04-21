package com.lxp.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String msg;
    private T data;

    public CommonResult(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}