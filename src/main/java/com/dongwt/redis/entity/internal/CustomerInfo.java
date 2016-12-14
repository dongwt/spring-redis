package com.dongwt.redis.entity.internal;

import java.io.Serializable;

import lombok.Data;


@Data
public class CustomerInfo implements Serializable {

    private static final long serialVersionUID = -7226907158331739406L;

    private String cusCode;

    private String cusName;

    private Integer editFlag;

}
