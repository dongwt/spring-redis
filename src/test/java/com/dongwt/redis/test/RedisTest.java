package com.dongwt.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.dongwt.redis.entity.User;
import com.dongwt.redis.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestConf.class)
@WebAppConfiguration
public class RedisTest {
    
    @Autowired
    private UserService UserService;
    
    @Test
    public void add(){
        User user = new User();
        user.setAge(18);
        user.setId(001);
        user.setUserName("tom");
        user.setSex(1);
        UserService.add(user);
    }
    
    @Test
    public void get(){
        User user = new User();
        user.setId(001);
        User result = UserService.get(user);
        System.out.println("user: " + JSON.toJSONString(result));
    }

}
