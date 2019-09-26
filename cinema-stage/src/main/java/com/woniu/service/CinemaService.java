package com.woniu.service;

import com.woniu.entity.Cinema;

import java.util.Date;
import java.util.List;

public interface CinemaService {
    //查询出该电影管理员名下所有的影院
    public List<Cinema> selectByAid(Integer pageIndex, Integer num, Integer aid) throws Exception;
    //根据影院id查询影院的相应信息
    public Cinema selectById(Integer cid) throws Exception;
    //查询电影表中的总数
    public Integer count(Integer aid) throws Exception;
    //新增一家电影院
    public Integer insert(Cinema cinema) throws Exception;
    //根据id删除一家影院
    public Integer delete(Integer cid) throws Exception;
    //根据id更新一家影院
    public Integer update(Cinema cinema) throws Exception;
    //根据城市名查询当地的影院
    List<Cinema> selectByCity(String city, Integer pageIndex, Integer num)throws Exception;
    //根据城市名查询数据总条数
    int getCountNumByCity(String city) throws Exception;
    //获取到该影厅上级影院套餐到期时间
    public List<String> getTime(Integer aid) throws Exception;
    //条件查询影院信息
    List<Cinema> getCinemas(String cinema,String city,String cinemaHall,Integer num,Integer pageIndex)throws Exception;
    // 查询数据总条数
    int getCountNum(String cinema,String city,String cinemaHall)throws Exception;

    List<Cinema> selectAllByAid(Integer aid) throws Exception;
}
