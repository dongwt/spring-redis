package com.dongwt.redis.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongwt.redis.api.response.Response;

@RestController
public class IndexCtrl {

    @RequestMapping("index.action")
    public Response<String> index() {
        Response<String> response = new Response<String>();
        response.setMessage("成功");
        response.setStatus(1);
        return response;
    }

}
