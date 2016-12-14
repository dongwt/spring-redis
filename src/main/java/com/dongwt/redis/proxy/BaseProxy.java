package com.dongwt.redis.proxy;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.api.response.TopicResponse;
import com.dongwt.redis.entity.QueueParams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BaseProxy implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 项目名
     */
    @Value("${redis.projectName}")
    protected String projectName;

    @Autowired
    protected RedisTemplate<String, QueueParams> redisTemplate;
    
    protected RedisSerializer<String>  stringSerializer;

    protected RedisSerializer<String>  keySerializer;

    protected RedisSerializer<TopicRequest>  requestSerializer;

    protected RedisSerializer<TopicResponse>  responseSerializer;
    
    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init(){
        stringSerializer = redisTemplate.getStringSerializer();
        keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
        requestSerializer = (RedisSerializer<TopicRequest>) redisTemplate.getValueSerializer();
        responseSerializer = (RedisSerializer<TopicResponse>) redisTemplate.getValueSerializer();
    }

}
