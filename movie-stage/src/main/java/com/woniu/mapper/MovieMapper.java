package com.woniu.mapper;

import com.woniu.entity.Movie;
import com.woniu.entity.Type;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface MovieMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Movie record);

    int insertSelective(Movie record);

    Movie selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Movie record);

    int updateByPrimaryKey(Movie record);

    Movie selectMovieByMid(Integer mid);
    List<Movie> selectMoviesByName(String mName);
    List<Movie> selectMoviesByCid(Integer cid,String nowTime);
    List<Movie> selectMOviesOnByCid(Integer cid);

    List<Movie> selectMovieListByScore(Integer num);

    List<Movie> selectMovieListByTime(Integer num);

    List<Movie> selectAllMovies();

    List<Movie> selectByPage(HashMap<String, Integer> map);

    Integer count();

    List<Movie> selectAfterMovies(Integer start, Integer num, Date today);

    Integer selectAfterCount(Date today);

    List<Movie> selectMoviesByExample(String type, String comntry, Date startTime, Date endTime, Integer start, Integer num);

    Integer selectCountByExample(String type, String comntry, Date startTime, Date endTime);

    List<Type> selectTypeByMid(Integer mid);
}