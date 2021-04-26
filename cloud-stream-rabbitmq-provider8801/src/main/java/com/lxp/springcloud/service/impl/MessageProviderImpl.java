package com.lxp.springcloud.service.impl;

import com.lxp.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

//定义消息的发送管道
@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;//消息通道Channel

    @Override
    public String send() {
        String msg = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(msg).build());
        System.out.println("---------------------msg:"+msg);
        return msg;
    }
}
