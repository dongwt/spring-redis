package com.dongwt.redis.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.api.response.TopicResponse;
import com.dongwt.redis.entity.User;
import com.dongwt.redis.handle.TopicRequestHandle;
import com.dongwt.redis.proxy.ProviderProxy;
import com.dongwt.redis.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {
    
    @Autowired
    private ProviderProxy<User> providerProxy;
    
    private String projectName = "projectName";

    @Override
    public void push() {
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setId(i);
            user.setUserName("tom" + i);
            TopicRequest<User> request = new TopicRequest<User>(projectName, UUID.randomUUID().toString(), user);
            providerProxy.save(request, new TopicRequestHandle<User>() {
                @Override
                public void callBack(TopicResponse<User> response) {
                    System.out.println("request: " + JSONObject.toJSONString(request));
                    System.out.println("ProviderProxy handle:" + JSONObject.toJSONString(response));
                }
            });
        }
    }

}