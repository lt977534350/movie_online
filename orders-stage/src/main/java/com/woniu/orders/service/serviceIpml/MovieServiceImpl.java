package com.woniu.orders.service.serviceIpml;

import com.woniu.orders.entity.Movie;
import com.woniu.orders.mapper.custom.MovieMapper;
import com.woniu.orders.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: movie_online
 * @description:
 * @author: liutao
 * @create: 2019-09-29 09:26
 **/
@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper movieMapper ;
    @Override
    public Movie selectMovieByMovieShoWTimeId(Integer msid) throws Exception {
      return movieMapper.selectMovieByMovieShowTimeId(msid);
    }
}
