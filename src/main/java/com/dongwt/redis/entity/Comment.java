package com.dongwt.redis.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Comment implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    
    private String CommentContent;

}
