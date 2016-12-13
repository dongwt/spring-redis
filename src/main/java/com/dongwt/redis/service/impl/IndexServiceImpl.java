package com.dongwt.redis.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.api.response.TopicResponse;
import com.dongwt.redis.handle.TopicRequestHandle;
import com.dongwt.redis.handle.TopicResponseHandle;
import com.dongwt.redis.proxy.ConsumerProxy;
import com.dongwt.redis.proxy.ProviderProxy;
import com.dongwt.redis.service.IndexService;

@Service
public class IndexServiceImpl implements IndexService{
    
    @Autowired
    private  ProviderProxy<String> providerProxy;
    
    @Autowired
    private  ConsumerProxy<String> consumerProxy;

    private String projectName = "projectName";
    

    @Override
    public void push() {
        for (int i = 0; i < 50; i++) {
            TopicRequest<String> request = new TopicRequest<String>(projectName, UUID.randomUUID().toString(), i + "");
            providerProxy.save(request, new TopicRequestHandle<String>() {
                private static final long serialVersionUID = 1L;
                @Override
                public void callBack(TopicResponse<String> response) {
                    System.out.println("ProviderProxy handle:" + JSON.toJSONString(response));
                }
            });
        }
    }

    @Override
    public void pop() {
        consumerProxy.bLPop(new TopicResponseHandle<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void callBack(TopicRequest<String> request) {
                System.out.println("ConsumerProxy handle:" + JSON.toJSONString(request));
            }
        });
    }
}
