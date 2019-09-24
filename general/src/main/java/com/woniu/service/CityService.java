package com.woniu.service;

import com.woniu.entity.City;

import java.util.List;

public interface CityService {
    /*查询所有区域*/
    List<City> getCitys()throws Exception;
    /*根据pid查询地区信息*/
    List<City> getCitysByCid(Integer pid)throws Exception;
}
