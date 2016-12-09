package com.dongwt.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class BaseRedisDao<K, V> {
    
    @Autowired  
    protected RedisTemplate<K, V> redisTemplate;

}
