package com.dongwt.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongwt.redis.dao.UserDao;
import com.dongwt.redis.entity.User;
import com.dongwt.redis.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserDao userDao;

    @Override
    public boolean add(User user) {
        return userDao.add(user);
    }

    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public User get(User user) {
        return userDao.get(user);
    }

}
