package com.woniu.service;

import com.woniu.entity.Cinema;

import java.util.Date;
import java.util.List;

public interface CinemaService {
    //查询出该电影管理员名下所有的影院
    public List<Cinema> selectByAid(Integer pageIndex, Integer num, Integer aid);
    //根据影院id查询影院的相应信息
    public Cinema selectById(Integer cid);
    //查询电影表中的总数
    public Integer count();
    //新增一家电影院
    public Integer insert(Cinema cinema);
    //根据id删除一家影院
    public Integer delete(Integer cid);
    //根据id更新一家影院
    public Integer update(Cinema cinema);
    //根据城市id查询当地的影院
    public List<Cinema> selectByCity(Integer ctid, Integer pageIndex, Integer num);
    //获取到该影厅上级影院套餐到期时间
    public List<String> getTime(Integer aid);
}
