package com.dongwt.redis.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.dongwt.redis.api.response.TopicResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestConf.class)
@WebAppConfiguration
public class QueueTest {
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    private String proName = "proName";
    
    @Test
    public void push(){
        redisTemplate.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
                RedisSerializer<String> valueSerializer = (RedisSerializer<String>) redisTemplate.getValueSerializer();
                byte[] key = keySerializer.serialize("test001");
                
                for(int i=0; i<20; i++){
                    byte[] value = valueSerializer.serialize(i + "");
                    connection.lPush(key, value);
                    System.out.println("lpush:" + i);
                }
                
                //订阅主题
                connection.subscribe(new MessageListener() {
                    @Override
                    public void onMessage(Message message, byte[] pattern) {
                        System.out.println("channel: " + valueSerializer.deserialize(message.getChannel()));
                        System.out.println("body: " + JSON.toJSONString(valueSerializer.deserialize(message.getBody())));
                        
                    }
                }, keySerializer.serialize(proName));
                
                return true;
            }
        });
    }
    
    @Test
    public void pop(){
        redisTemplate.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
                RedisSerializer<String> valueSerializer = (RedisSerializer<String>) redisTemplate.getValueSerializer();
                byte[] key = keySerializer.serialize("test001");
                
                boolean flag = true;
                while(flag){
                    List<byte[]> values = connection.bLPop(120, key);
                    
                    for(byte[] value : values){
                        System.out.println(valueSerializer.deserialize(value));
                    }
                    
                    String message = valueSerializer.deserialize(values.get(1));
                    TopicResponse<String> response = new TopicResponse<String>(message,message);
                    RedisSerializer<TopicResponse<String>> topicSerializer = (RedisSerializer<TopicResponse<String>>) redisTemplate.getValueSerializer();
                    //发布主题
                    connection.publish(keySerializer.serialize(proName), topicSerializer.serialize(response));
                }
                
                return true;
            }
        });
    }

}
