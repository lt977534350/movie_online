package com.woniu.jobs.entity;

import lombok.Data;

import java.util.List;

@Data
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
   private Integer msid ;

    private List<Seatinfo> son;

    public Seatinfo() {
    }

    public Seatinfo(String row, String column) {
        this.row = row;
        this.column = column;
    }
}