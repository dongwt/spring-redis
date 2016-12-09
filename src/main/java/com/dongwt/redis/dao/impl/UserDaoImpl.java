package com.dongwt.redis.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import com.dongwt.redis.dao.BaseRedisDao;
import com.dongwt.redis.dao.UserDao;
import com.dongwt.redis.entity.User;

@Repository
public class UserDaoImpl extends BaseRedisDao<String, User> implements UserDao {

    @Override
    public boolean add(User user) {
        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
                RedisSerializer<User> valueSerializer = (RedisSerializer<User>) redisTemplate.getValueSerializer();
                byte[] key = keySerializer.serialize(String.valueOf(user.getId()));
                byte[] value = valueSerializer.serialize(user);
                connection.set(key, value);
                return true;
            }

        });
        return result;
    }

    @Override
    public boolean delete(User user) {
        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
                byte[] key = keySerializer.serialize(String.valueOf(user.getId()));
                connection.del(key);
                return true;
            }

        });
        return result;
    }

    @Override
    public boolean update(User user) {
        if (this.get(user) == null) {
            throw new NullPointerException("数据行不存在, id = " + user.getId());
        }

        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
                RedisSerializer<User> valueSerializer = (RedisSerializer<User>) redisTemplate.getValueSerializer();
                byte[] key = keySerializer.serialize(String.valueOf(user.getId()));
                byte[] value = valueSerializer.serialize(user);
                connection.set(key, value);
                return true;
            }

        });
        return result;
    }

    @Override
    public User get(User user) {
        User result = redisTemplate.execute(new RedisCallback<User>() {
            @Override
            public User doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
                RedisSerializer<User> valueSerializer = (RedisSerializer<User>) redisTemplate.getValueSerializer();
                byte[] key = keySerializer.serialize(String.valueOf(user.getId()));
                byte[] value = connection.get(key);
                return valueSerializer.deserialize(value);
            }

        });
        return result;
    }

}
