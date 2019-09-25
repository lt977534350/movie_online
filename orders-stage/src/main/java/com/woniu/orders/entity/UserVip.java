package com.woniu.orders.entity;

public class UserVip {
    /**
     * 用户VIP表id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * VIP表id
     */
    private Integer vid;

    /**
     * 消费的money
     */
    private Double consume;

    /**
     * 影院id
     */
    private Integer aid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Double getConsume() {
        return consume;
    }

    public void setConsume(Double consume) {
        this.consume = consume;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }
}