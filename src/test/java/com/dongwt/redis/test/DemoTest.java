package com.dongwt.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestConf.class)
@WebAppConfiguration
@Slf4j
public class DemoTest {
    
    @Value("${redis.projectName}")
    private String projectName;


    @Test
    public void test(){
        log.info("projectName {}",projectName);
    }
        
        
}
