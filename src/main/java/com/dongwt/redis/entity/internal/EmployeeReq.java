package com.dongwt.redis.entity.internal;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmployeeReq implements Serializable{
    
    
    private static final long serialVersionUID = 1L;

    private String code;
    
    private String name;
}
