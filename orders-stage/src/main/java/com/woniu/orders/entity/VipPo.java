package com.woniu.orders.entity;

import lombok.Data;

@Data
public class VipPo {
    /**
    * vip表id
    */
    private Integer id;

    /**
    * vip 的名称：普通VIP和svip
    */
    private String vname;

    /**
    * 折扣比例
    */
    private Double vdiscount;

    /**
    * 影院id
    */
    private Integer aid;

    private Double quota;
}