package com.dongwt.redis.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 
 * Function: 队列参数实体
 *
 * @author   董纹陶
 * @Date	 2016年12月14日		下午3:55:39
 *
 * @see
 */
@Data
public class QueueParams implements Serializable{

    private static final long serialVersionUID = 1L;
    
    /**
     * 队列id
     */
    private Integer id;
    
    /**
     * 单据号
     */
    private String voucherNumber;
    
    /**
     * 项目名
     */
    private String projectName;
    
    
    /**
     * 来源  1.erp 2.新房财务 3.小区合伙人
     */
    private Integer origin;
    
    /**
     * 请求实体 
     */
    private String requestBody;
    
    /**
     * 响应实体
     */
    private String responseBody;
    
    /**
     * 处理状态  0 未处理  1 已处理 
     */
    private Integer handleStatus;
    
    /**
     * 是否可用  0不可用  1可用
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
