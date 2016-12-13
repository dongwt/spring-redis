package com.dongwt.redis.proxy;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import com.dongwt.redis.api.request.TopicRequest;
import com.dongwt.redis.api.response.TopicResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BaseProxy<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    protected RedisTemplate<String, T> redisTemplate;

    
    protected RedisSerializer<String>  stringSerializer;

    protected RedisSerializer<String>  keySerializer;

    protected RedisSerializer<TopicRequest<T>>  requestSerializer;

    protected RedisSerializer<TopicResponse<T>>  responseSerializer;
    
    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init(){
        stringSerializer = redisTemplate.getStringSerializer();
        keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
        requestSerializer = (RedisSerializer<TopicRequest<T>>) redisTemplate.getValueSerializer();
        responseSerializer = (RedisSerializer<TopicResponse<T>>) redisTemplate.getValueSerializer();
    }

}
