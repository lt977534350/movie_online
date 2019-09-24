package com.woniu.myutil.myeneity;

public class Menu {
    /**
    * 套餐id
    */
    private Integer id;

    /**
    * 套餐名字
    */
    private String name;

    /**
    * 套餐资费
    */
    private Double money;

    /**
    * 套餐的使用年限
    */
    private Integer period;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}