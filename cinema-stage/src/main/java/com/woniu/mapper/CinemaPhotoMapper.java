package com.woniu.mapper;

import com.woniu.entity.CinemaPhoto;

public interface CinemaPhotoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CinemaPhoto record);

    int insertSelective(CinemaPhoto record);

    CinemaPhoto selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CinemaPhoto record);

    int updateByPrimaryKey(CinemaPhoto record);

    void deleteByPid(Integer pid);
}