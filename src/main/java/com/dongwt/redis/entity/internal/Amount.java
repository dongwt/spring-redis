package com.dongwt.redis.entity.internal;

import java.io.Serializable;

import lombok.Data;

@Data
public class Amount implements Serializable{

    private static final long serialVersionUID = 1L;

    private String currencyNumber;
    
    private double originalAmount;
    
    private double debitAmount;
    
    private double creditAmount;
    
}
