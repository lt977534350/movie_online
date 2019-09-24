package com.woniu.mapper;

import com.woniu.entity.Cinema;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface CinemaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cinema record);

    int insertSelective(Cinema record);

    Cinema selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cinema record);

    int updateByPrimaryKey(Cinema record);

    List<Cinema> selectByAid(HashMap<String, Integer> map);

    Integer count();

    List<Cinema> selectAll();

    List<Cinema> selectByCity(HashMap<String,Integer> map);

    Date getOverTime(Integer aid);

    List<Cinema> selectAllByAid(Integer aid);
}