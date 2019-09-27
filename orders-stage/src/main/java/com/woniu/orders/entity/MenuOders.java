package com.woniu.orders.entity;

import java.util.Date;
import lombok.Data;

@Data
public class MenuOders {
    private Integer id;

    private String menuodersnum;

    /**
     * 影院id
     */
    private Integer aid;

    private Date cTime;

    private Date paySuccessTime;

    private Double menoy;

    /**
     * 标记几年的套餐
     */
    private Integer num;
}