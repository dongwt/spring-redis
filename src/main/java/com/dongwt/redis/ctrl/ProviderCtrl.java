package com.dongwt.redis.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongwt.redis.api.response.Response;
import com.dongwt.redis.service.ProviderService;

@RestController
@RequestMapping("redis")
public class ProviderCtrl {
    
    @Autowired
    private ProviderService providerService;
    
    
    @RequestMapping("push.action")
    public Response<String> push() {
        Response<String> response = new Response<String>();
        
        System.out.println("push...");
        
        providerService.push();

        response.setMessage("成功");
        response.setStatus(1);
        return response;
    }

}
