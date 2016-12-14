package com.dongwt.redis.entity.internal;

import java.io.Serializable;

import lombok.Data;

@Data
public class JdWSWSVoucher implements Serializable{
    
    
    private static final long serialVersionUID = 1L;

    private TableHead tableHead;
    
    private Entries[] entries;
    
    private Integer origin;
    
    private String uuid;
}
