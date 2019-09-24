package com.woniu.mapper;

import com.woniu.entity.MovieShowtime;

import java.util.List;

public interface MovieShowtimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MovieShowtime record);

    int insertSelective(MovieShowtime record);

    MovieShowtime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MovieShowtime record);

    int updateByPrimaryKey(MovieShowtime record);

    List<MovieShowtime> selectByCidAndMid(Integer cid,Integer mid);
}