package com.woniu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    /**
    * 图片id
    */
    private Integer id;

    /**
    * 图片的url
    */
    private String url;

    /**
    * 类型：影院设施图片，电影简介图片
    */
    private String type;
}