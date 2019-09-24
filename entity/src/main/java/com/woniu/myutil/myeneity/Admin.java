package com.woniu.myutil.myeneity;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public String getOverDate() {
        return overDate;
    }

    public void setOverDate(String overDate) {
        this.overDate = overDate;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}
