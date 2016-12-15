package com.dongwt.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dongwt.redis.service.ProviderService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestConf.class)
@WebAppConfiguration
@Slf4j
public class DemoTest {
    
    @Autowired
    private ProviderService providerService;
    


    @Test
    public void test(){
        
        providerService.getHandleList();
    }
        
        
}
