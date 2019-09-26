package com.woniu.orders.util;

import java.util.Date;

/**
 * @program: movie_online
 * @description:
 * @author: liutao
 * @create: 2019-09-25 19:44
 **/
public class CountDetail {
    private String date;
    private String count;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CountDetail{" +
                "date=" + date +
                ", count='" + count + '\'' +
                '}';
    }
}
