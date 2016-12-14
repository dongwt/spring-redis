package com.dongwt.redis.callback.impl;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.dongwt.redis.api.response.TopicResponse;
import com.dongwt.redis.callback.TopicRequestCallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserTopicRequestCallback<T> implements TopicRequestCallback<T>{

    @Override
    public void onResponseEvent(TopicResponse<T> response) {
        log.info("callBack:{}",JSONObject.toJSONString(response));
    }

}
