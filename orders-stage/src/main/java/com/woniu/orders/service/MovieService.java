package com.woniu.orders.service;

import com.woniu.orders.entity.Movie;

/**
 * @program: movie_online
 * @description:
 * @author: liutao
 * @create: 2019-09-29 09:25
 **/
public interface MovieService {
    Movie selectMovieByMovieShoWTimeId(Integer msid) throws Exception;
}
