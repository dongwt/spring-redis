package com.dongwt.redis.test;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.api.response.TopicResponse;
import com.dongwt.redis.handle.TopicRequestHandle;
import com.dongwt.redis.handle.TopicResponseHandle;
import com.dongwt.redis.proxy.ConsumerProxy;
import com.dongwt.redis.proxy.ProviderProxy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestConf.class)
@WebAppConfiguration
public class ProxyTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private String projectName = "projectName";

    @Test
    public void push() {

    

    }

    @Test
    public void pop() {
      
    }

}
