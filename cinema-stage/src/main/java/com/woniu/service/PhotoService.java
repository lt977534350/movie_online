package com.woniu.service;

import com.woniu.entity.Photo;

import java.util.List;

public interface PhotoService {

    List<Photo> selectPicByAid(Integer aid)throws Exception;

    void deleteById(Integer id)throws Exception;
}
