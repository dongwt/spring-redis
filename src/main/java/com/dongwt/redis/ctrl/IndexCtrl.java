package com.dongwt.redis.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongwt.redis.api.response.Response;

@Controller
public class IndexCtrl {
    
    @RequestMapping("index.action")
    @ResponseBody
    public Response<String> index(){
        Response<String> response = new Response<String>();
        response.setMessage("成功");
        response.setStatus(1);
        return response;
    }

}
