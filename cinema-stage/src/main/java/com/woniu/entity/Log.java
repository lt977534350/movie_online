package com.woniu.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Log {
    /**
     * 日志记录的id
     */
    private Integer id;

    /**
     * 操作人的姓名
     */
    private String username;

    /**
     * 操作的具体信息
     */
    private String operation;

    /**
     * 操作的时间
     */
    private Date optime;
    private String formatTime;


    /**
     * 操作的级别：1.normal,2.error,3.danger
     */
    private String optype;
}