package com.woniu.mapper;

import com.woniu.entity.Seatinfo;

public interface SeatinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Seatinfo record);

    int insertSelective(Seatinfo record);

    Seatinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Seatinfo record);

    int updateByPrimaryKey(Seatinfo record);
}