package com.dongwt.redis.api.response;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Response<T> implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer status;
    
    private String message;
    
    private T data;

}
