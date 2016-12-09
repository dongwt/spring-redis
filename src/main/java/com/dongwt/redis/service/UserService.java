package com.dongwt.redis.service;

import com.dongwt.redis.entity.User;

public interface UserService {
    boolean add(User user);

    boolean delete(User user);

    boolean update(User user);

    User get(User user);
}
