package com.woniu.orders.entity;

import lombok.Data;

@Data
public class UserVipPO {
    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 影院id
     */
    private Integer aid;

    /**
     * VIP表id
     */
    private Integer vid;

    private Double consume;
}