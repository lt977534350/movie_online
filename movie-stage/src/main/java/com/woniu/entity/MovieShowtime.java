package com.woniu.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class MovieShowtime implements Serializable {
    /**
    * 电影放映表id
    */
    private Integer id;

    /**
    * 影院id
    */
    private Integer cid;

    /**
    * 电影id
    */
    private Integer mid;

    /**
    * 放映时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date playtime;

    /**
    * 影厅id
    */
    private Integer roomId;

    /**
    * 票价
    */
    private Double money;

    private CinemaRoom cinemaRoom;
}