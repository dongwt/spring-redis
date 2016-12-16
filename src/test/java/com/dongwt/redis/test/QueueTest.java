package com.dongwt.redis.test;

import java.util.Random;
import java.util.UUID;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.dongwt.redis.entity.internal.JdWSWSVoucher;
import com.dongwt.redis.entity.internal.TableHead;
import com.dongwt.redis.utils.BugUtils;

public class QueueTest {

    
    @Test
    public void test(){
        
        String url = "http://192.168.188.129:8080/spring-redis/redis/push.action";
        JdWSWSVoucher jdWSWSVoucher;
        Random ra =new Random();
        
        
        for(int i=0; i<5; i++){
            jdWSWSVoucher = new JdWSWSVoucher();
            jdWSWSVoucher.setOrigin(ra.nextInt(3) + 1);
            jdWSWSVoucher.setUuid(UUID.randomUUID().toString());
            
            TableHead tableHead = new TableHead();
            tableHead.setVoucherNumber("V" + UUID.randomUUID().toString());
            jdWSWSVoucher.setTableHead(tableHead);
            String json = JSONObject.toJSONString(jdWSWSVoucher);
            BugUtils.post(url, json);
        }
       
        
    }

}
