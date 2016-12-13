package com.dongwt.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.entity.User;
import com.dongwt.redis.handle.TopicResponseHandle;
import com.dongwt.redis.proxy.ConsumerProxy;
import com.dongwt.redis.service.ConsumerService;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerProxy<User> consumerProxy;


    @Override
    public void pop() {
        consumerProxy.bLPop(new TopicResponseHandle<User>() {

            @Override
            public void callBack(TopicRequest<User> request) {

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
