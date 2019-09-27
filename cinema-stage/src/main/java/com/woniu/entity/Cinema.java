package com.woniu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema implements Serializable {
    /**
    * 影院id
    */
    private Integer id;

    /**
    * 影院名
    */
    private String cName;

    /**
    * 影院地址
    */
    private String cAddress;

    /**
    * 版权
    */
    private String copyRight;

    /**
    * 影院图片
    */
    private String facility;

    /**
     * 影院管理人id
     */
    private int aid;

    /**
     * 影院的电话
     */
    private String phone;

    /**
     * 影院设施图片集合
     */
    private List<Picture> pics;

    /**
     * 放映厅所在的城市
     */
    private String city;

    /**
     * 影厅的经纬度
     */
    private String coordinate;

}