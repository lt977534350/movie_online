package com.woniu.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
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
     * 评分星星数
     */
    private Integer starnum;

    /**
     * 空星星的数量
     */
    private Integer emstarnum;

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

    /**
     * 电影类型
     */
    private List<Type> types;

    private String tostar;

    /**
     * 电影图片链接
     */
    private List<Photo> photos;

    /**
     * 电影排片信息
     */
    private List<MovieShowtime> movieShowtimes;

    /**
     * 电影人员表
     */
    private MoviePerson moviePerson;
}