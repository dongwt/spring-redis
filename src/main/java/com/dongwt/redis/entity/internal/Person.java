package com.dongwt.redis.entity.internal;

import java.io.Serializable;

import lombok.Data;


@Data
public class Person implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String name;
    
    private String mobile;
    
    private Integer isMain;
}
