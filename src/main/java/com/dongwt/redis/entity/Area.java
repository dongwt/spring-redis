package com.dongwt.redis.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Area implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String unitId;
    
    private List<Comment> unitComments;
    
    private String qyName;
    
    private String bkName;
    
    private String xqName;
    
    private String address;
    
    /**
     * 总页数
     */
    private Integer totalPage;
    
    /**
     * 总记录数
     */
    private Integer totalCount;

}
