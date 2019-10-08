package com.woniu.service;

import com.woniu.entity.MovieShowtime;

import java.util.List;

public interface MovieShowtimeService {
    List<MovieShowtime> selectByCidAndMid(Integer cid,Integer mid)throws Exception;
    void insertOneData(MovieShowtime movieShowtime);

    void updateById(MovieShowtime movieShowtime);

    void deleteById(Integer msid);
}
