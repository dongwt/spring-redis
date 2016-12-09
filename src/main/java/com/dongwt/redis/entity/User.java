package com.dongwt.redis.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;

    private String userName;
    
    private Integer age;
    
    private Integer sex;
}
