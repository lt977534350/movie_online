package com.woniu.mapper;

import com.woniu.entity.Cinema;
import org.apache.ibatis.annotations.Param;

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

        Integer count(Integer aid);

        List<Cinema> selectAll();

        /*根据电影名称查询cinema*/
        List<Cinema> selectByCity(HashMap<String, Object> map);
        /*查询数据总条数*/
        int getCountNumByCity(@Param("city") String city);

        Date getOverTime(Integer aid);

        /*条件查询影院信息*/
        List<Cinema> getCinemas(@Param("cinema") String cinema, @Param("city") String city, @Param("cinemaHall") String cinemaHall,@Param("num") Integer num,@Param("start") Integer start);
        /*查询数据总条数*/
        int getCountNum(@Param("cinema") String cinema, @Param("city") String city, @Param("cinemaHall") String cinemaHall);

    List<Cinema> selectAllByAid(Integer aid);
}