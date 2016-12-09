package com.dongwt.redis.dao;

import com.dongwt.redis.entity.User;

public interface UserDao {
    
    boolean add(User user);
    
    boolean delete(User user);
    
    boolean update(User user);
    
    User get(User user);

}
