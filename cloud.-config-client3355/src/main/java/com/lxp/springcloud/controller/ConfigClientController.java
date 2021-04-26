package com.lxp.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.info}")
    //config.info是bootstrap.yaml中配置的master分支下，config-dev的文件中的内容(下面)，此时已经被加载到了配置文件中，可以通过config.info直接获取
    // config: info: master branch,springcloud-config/config-dev.yml version=7
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}
