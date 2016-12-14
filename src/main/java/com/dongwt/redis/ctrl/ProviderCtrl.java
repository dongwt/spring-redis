package com.dongwt.redis.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongwt.redis.api.response.Response;
import com.dongwt.redis.entity.internal.JdWSWSVoucher;
import com.dongwt.redis.service.ProviderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("redis")
public class ProviderCtrl {
    
    @Autowired
    private ProviderService providerService;

    @RequestMapping("push.action")
    public Response<String> push(@RequestBody JdWSWSVoucher jdWSWSVoucher) {
        Response<String> response = new Response<String>();

        log.info("push...");
        providerService.lPush(jdWSWSVoucher);

        response.setMessage("成功");
        response.setStatus(1);
        return response;
    }

}
