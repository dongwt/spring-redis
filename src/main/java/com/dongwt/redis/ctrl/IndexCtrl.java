package com.dongwt.redis.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongwt.redis.api.response.Response;
import com.dongwt.redis.service.IndexService;

@RestController
public class IndexCtrl {
    
    @Autowired
    private IndexService indexService;


    @RequestMapping("index.action")
    public Response<String> index() {
        Response<String> response = new Response<String>();
        response.setMessage("成功");
        response.setStatus(1);
        return response;
    }

    @RequestMapping("push.action")
    public Response<String> push() {
        Response<String> response = new Response<String>();
        
        indexService.push();

        response.setMessage("成功");
        response.setStatus(1);
        return response;
    }
    
    
    @RequestMapping("pop.action")
    public Response<String> pop() {
        Response<String> response = new Response<String>();
       
        indexService.pop();
        
        response.setMessage("成功");
        response.setStatus(1);
        return response;
    }

}
