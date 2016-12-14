package com.dongwt.redis.Listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.handle.TopicResponseHandle;
import com.dongwt.redis.proxy.ConsumerProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ConsumerListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConsumerProxy<?> consumerProxy;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("ConsumerListener {} start complete.",consumerProxy.getProjectName());
        consumerProxy.bLPop(new TopicResponseHandle() {

            @Override
            public void callBack(TopicRequest request) {

                try {
                    log.info("handel business.....");
                    Thread.sleep(1000 * 5);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
