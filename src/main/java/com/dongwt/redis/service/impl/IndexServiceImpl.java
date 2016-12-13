package com.dongwt.redis.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.api.response.TopicResponse;
import com.dongwt.redis.entity.User;
import com.dongwt.redis.handle.TopicRequestHandle;
import com.dongwt.redis.handle.TopicResponseHandle;
import com.dongwt.redis.proxy.ConsumerProxy;
import com.dongwt.redis.proxy.ProviderProxy;
import com.dongwt.redis.service.IndexService;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private ProviderProxy<User> providerProxy;

    @Autowired
    private ConsumerProxy<User> consumerProxy;

    private String projectName = "projectName";

    @Override
    public void push() {
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setId(i);
            user.setUserName("tom" + i);
            TopicRequest<User> request = new TopicRequest<User>(projectName, UUID.randomUUID().toString(), user);
            providerProxy.save(request, new TopicRequestHandle<User>() {
                private static final long serialVersionUID = 1L;
                @Override
                public void callBack(TopicResponse<User> response) {
                    System.out.println("request: " + JSONObject.toJSONString(request));
                    System.out.println("ProviderProxy handle:" + JSONObject.toJSONString(response));
                }
            });
        }
    }

    @Override
    public void pop() {
        consumerProxy.bLPop(new TopicResponseHandle<User>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void callBack(TopicRequest<User> request) {
                //                System.out.println("ConsumerProxy handle:" + JSON.toJSONString(request));
            }
        });
    }
}
