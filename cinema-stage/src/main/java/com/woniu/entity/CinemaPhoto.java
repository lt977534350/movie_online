package com.woniu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaPhoto {
    private Integer id;

    /**
    * 影院id
    */
    private Integer cid;

    /**
    * 图片id
    */
    private Integer pid;
}