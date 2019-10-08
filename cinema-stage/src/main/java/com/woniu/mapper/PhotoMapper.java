package com.woniu.mapper;

import com.woniu.entity.Photo;

import java.util.List;

public interface PhotoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Photo record);

    int insertSelective(Photo record);

    Photo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);

    List<Photo> selectPicByAid(Integer aid);
}