package com.dongwt.redis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongwt.redis.dao.read.ConsumerReadMapper;
import com.dongwt.redis.dao.write.ConsumerWriteMapper;
import com.dongwt.redis.entity.QueueParams;
import com.dongwt.redis.service.ConsumerService;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    
    @Autowired
    private ConsumerReadMapper consumerReadMapper;
    
    @Autowired
    private ConsumerWriteMapper consumerWriteMapper;

    @Override
    public List<QueueParams> getUnHandleList() {
        return consumerReadMapper.getUnHandleList();
    }

    @Override
    public void update(QueueParams queueParams) {
        consumerWriteMapper.update(queueParams);
    }


}
