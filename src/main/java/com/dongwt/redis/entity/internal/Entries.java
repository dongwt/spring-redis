package com.dongwt.redis.entity.internal;

import java.io.Serializable;

import lombok.Data;


@Data
public class Entries implements Serializable {

    private static final long serialVersionUID = 1L;

    private int entrySeq;

    private String voucherAbstract;

    private String accountNumber;

    private String settlementType;

    private int entryDC;

    private Amount amount;

    private DividedAccount dividedAccount;

}
