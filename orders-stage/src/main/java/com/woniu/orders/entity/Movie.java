package com.woniu.orders.entity;

import java.util.Date;
import java.util.List;




public class Movie {
    /**
     * 电影id
     */
    private Integer id;

    /**
     * 电影名
     */
    private String mname;

    /**
     * 票房
     */
    private Double tickets;

    /**
     * 国家
     */
    private String comntry;

    /**
     * 上映时间
     */
    private Date uptime;

    /**
     * 评分
     */
    private String score;

    /**
     * 简介
     */
    private String shortStory;

    /**
     * 预告片
     */
    private String preMovie;

    /**
     * 电影时长
     */
    private String longtime;

    /**
     * 电影的封面图
     */
    private String mpic;

    private List<Type> types;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public Double getTickets() {
        return tickets;
    }

    public void setTickets(Double tickets) {
        this.tickets = tickets;
    }

    public String getComntry() {
        return comntry;
    }

    public void setComntry(String comntry) {
        this.comntry = comntry;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getShortStory() {
        return shortStory;
    }

    public void setShortStory(String shortStory) {
        this.shortStory = shortStory;
    }

    public String getPreMovie() {
        return preMovie;
    }

    public void setPreMovie(String preMovie) {
        this.preMovie = preMovie;
    }

    public String getLongtime() {
        return longtime;
    }

    public void setLongtime(String longtime) {
        this.longtime = longtime;
    }

    public String getMpic() {
        return mpic;
    }

    public void setMpic(String mpic) {
        this.mpic = mpic;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }
}