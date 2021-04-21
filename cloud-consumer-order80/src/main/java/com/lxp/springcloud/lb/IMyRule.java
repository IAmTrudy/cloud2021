package com.lxp.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface IMyRule {

    /**
     * 根据算法返回某个服务
     * @param list 服务列表
     * @return
     */
    ServiceInstance instance(List<ServiceInstance> list);
}
