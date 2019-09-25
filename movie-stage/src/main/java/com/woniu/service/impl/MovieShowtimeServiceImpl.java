package com.woniu.service.impl;

import com.woniu.entity.MovieShowtime;
import com.woniu.mapper.MovieShowtimeMapper;
import com.woniu.service.MovieShowtimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MovieShowtimeServiceImpl implements MovieShowtimeService {
    @Resource
    private MovieShowtimeMapper movieShowtimeMapper;
    @Override
    public List<MovieShowtime> selectByCidAndMid(Integer cid,Integer mid) throws Exception {
        return movieShowtimeMapper.selectByCidAndMid(cid,mid);
    }

    @Override
    public void insertOneData(MovieShowtime movieShowtime) {
        movieShowtimeMapper.insertSelective(movieShowtime);
    }

    @Override
    public void updateById(MovieShowtime movieShowtime) {
        movieShowtimeMapper.updateByPrimaryKey(movieShowtime);
    }
}
