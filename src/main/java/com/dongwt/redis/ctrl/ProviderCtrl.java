package com.dongwt.redis.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value="push.action",method=RequestMethod.POST)
    public Response<String> push(@RequestBody JdWSWSVoucher jdWSWSVoucher) {
        log.info("push...");
        Response<String> response  = providerService.lPush(jdWSWSVoucher);
        return response;
    }

}
