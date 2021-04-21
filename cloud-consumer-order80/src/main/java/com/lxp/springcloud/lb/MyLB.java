package com.lxp.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements IMyRule {

    //Integer的原子类
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //防止获得的next已经被拿走
    public final int getAndIncrement(){
        int current;
        int next;
        for(;;){
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current+1;
            if(this.atomicInteger.compareAndSet(current,next)){
                break;
            }
        }
        return next;
    }

    //实现
    @Override
    public ServiceInstance instance(List<ServiceInstance> list) {
        int i = getAndIncrement() % list.size();
        return list.get(i);
    }
}
