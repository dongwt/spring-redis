package com.dongwt.redis.entity.internal;

import java.io.Serializable;
import java.util.function.Supplier;

import lombok.Data;


@Data
public class DividedAccount implements Serializable{
    
    
    private static final long serialVersionUID = 1L;

    private double fzAmount;
    
    private BusinessLine businessLine;
    
    private HouseResource houseResource;
    
    private NewHouseProject newHouseProject;
    
    private String developerName;
    
    private EmployeeReq clerk;
    
    private Department dep;
    
    private Person[] buys;
    
    private Person[] sales;

    private CustomerInfo cusInfo;
    
    private String bankAccount;

    private Supplier supplier;
    
}
