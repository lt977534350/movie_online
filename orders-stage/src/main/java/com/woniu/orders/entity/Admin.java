package com.woniu.orders.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Admin {
    /**
    * 管理员id
    */
    private Integer id;

    /**
    * 管理员名
    */
    private String username;

    /**
    * 电话号码
    */
    private String phone;

    /**
    * 密码
    */
    private String password;

    /**
    * 管理等级：0代表平台管理，1：代表影院管理
    */
    private Byte level;

    /**
    * 每一家影城的logo
    */
    private String logo;

    /**
    * 影院管理员的影院名称
    */
    private String name;

    /**
    * 套餐到期时间
    */
    private Date overtime;

    /**
    * 影城的版权信息
    */
    private String copyRight;
}