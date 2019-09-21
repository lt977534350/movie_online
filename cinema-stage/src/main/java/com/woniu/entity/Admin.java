package com.woniu.entity;

import lombok.Data;

import java.util.Date;

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
     * 电影管理员名下的影院的LOGO
     */
    private String logo;

    /**
     * 电影管理员名下的影城名字
     */
    private String name;

    /**
     * 套餐过期时间
     */
    private Date overTime;

    /**
     * 套餐时间格式化显示
     */
    private String overDate;

    /**
     * 电影管理员所购套餐的详情
     */
    private Menu menu;
}