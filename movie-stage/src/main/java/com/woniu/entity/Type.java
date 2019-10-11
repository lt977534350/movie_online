package com.woniu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Type  implements Serializable {
    private  static final long serialVersionUID = 2629397188L;
    /**
    * 类型id
    */
    private Integer id;

    /**
    * 电影类型
    */
    private String mtype;
}