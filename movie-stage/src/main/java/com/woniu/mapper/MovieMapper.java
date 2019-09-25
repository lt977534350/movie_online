package com.woniu.mapper;

import com.woniu.entity.Movie;

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
    List<Movie> selectMoviesByCid(Integer cid);
    List<Movie> selectMOviesOnByCid(Integer cid);

    List<Movie> selectMovieListByScore(Integer num);

    List<Movie> selectMovieListByTime(Integer num);

    List<Movie> selectAllMovies();

    List<Movie> selectByPage(HashMap<String, Integer> map);

    Integer count();

}