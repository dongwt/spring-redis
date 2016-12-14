package com.dongwt.redis.entity.internal;

import java.io.Serializable;

import lombok.Data;


@Data
public class TableHead implements Serializable{
    
    
    private static final long serialVersionUID = 1L;

    private String itemNumber;
    
    private String voucherNumber;
    
    private Integer djType;
    
    private String voucherType;
    
    private String bizDate;
    
    private ChildCompany companyNumber;
    
    private EmployeeReq creator;
    
    private String createdDate;

}
