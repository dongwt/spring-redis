package com.dongwt.redis.callback.impl;

import org.springframework.stereotype.Component;

import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.callback.TopicHandleCallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TopicHandleCallbackImpl implements TopicHandleCallback {

    @Override
    public void onRequestEvent(TopicRequest request) {
        log.info("onRequestEvent handel business.....");
        try {
            Thread.sleep(1000 * 5);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
