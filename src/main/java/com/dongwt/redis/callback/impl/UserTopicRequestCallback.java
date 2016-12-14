package com.dongwt.redis.callback.impl;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.dongwt.redis.api.response.TopicResponse;
import com.dongwt.redis.callback.TopicRequestCallback;

@Component
public class UserTopicRequestCallback<T> implements TopicRequestCallback<T>{

    @Override
    public void onResponseEvent(TopicResponse<T> response) {
        System.out.println("callBack:" + JSONObject.toJSONString(response));
    }

}
