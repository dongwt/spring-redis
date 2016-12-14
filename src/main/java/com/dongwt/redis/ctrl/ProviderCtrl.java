package com.dongwt.redis.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongwt.redis.api.response.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("redis")
public class ProviderCtrl {

    @RequestMapping("push.action")
    public Response<String> push() {
        Response<String> response = new Response<String>();

        log.info("push...");

        response.setMessage("成功");
        response.setStatus(1);
        return response;
    }

}
