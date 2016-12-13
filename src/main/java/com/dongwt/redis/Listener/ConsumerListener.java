package com.dongwt.redis.Listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.dongwt.redis.service.ConsumerService;

@Component
public class ConsumerListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConsumerService consumerService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("ConsumerListener...");
        consumerService.pop();
    }

}
