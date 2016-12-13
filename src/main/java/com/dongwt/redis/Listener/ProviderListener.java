package com.dongwt.redis.Listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.dongwt.redis.proxy.ProviderProxy;

@Component
public class ProviderListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ProviderProxy<?> providerProxy;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("ProviderListener...");
        providerProxy.subscribe();
    }

}
