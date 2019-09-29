package com.woniu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seatinfo {
    /**
    * 座位id
    */
    private Integer id;

    /**
    * 行
    */
    private String row;

    /**
    * 列
    */
    private String column;

    /**
    * 状态，N 没卖出，LK，已卖出，E，清空
    */
    private String state;

    /**
    * 排票表id
    */
    private Integer msid;
}