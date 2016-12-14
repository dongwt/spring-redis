package com.dongwt.redis.entity.internal;

import java.io.Serializable;

import lombok.Data;

@Data
public class NewHouseProject implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String code;
    
    private String name;
}
