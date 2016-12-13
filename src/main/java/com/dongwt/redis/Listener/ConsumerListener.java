package com.dongwt.redis.Listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.handle.TopicResponseHandle;
import com.dongwt.redis.proxy.ConsumerProxy;

@Component
public class ConsumerListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConsumerProxy<?> consumerProxy;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("ConsumerListener...");
        consumerProxy.bLPop(new TopicResponseHandle() {

            @Override
            public void callBack(TopicRequest request) {

                try {
                    System.out.println("handel.....");
                    Thread.sleep(1000 * 5);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
