package com.dongwt.redis.ctrl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.api.response.Response;
import com.dongwt.redis.entity.User;
import com.dongwt.redis.proxy.ProviderProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("redis")
public class ProviderCtrl {

    @Autowired
    private ProviderProxy<User> providerProxy;

    @Value("${redis.projectName}")
    private String projectName;

    @RequestMapping("push.action")
    public Response<String> push() {
        Response<String> response = new Response<String>();
        
        log.info("push...");

        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setId(i);
            user.setUserName("tom" + i);
            TopicRequest<User> request = new TopicRequest<User>(UUID.randomUUID().toString(), user);
            providerProxy.lPush(request);
        }

        response.setMessage("成功");
        response.setStatus(1);
        return response;
    }

}
