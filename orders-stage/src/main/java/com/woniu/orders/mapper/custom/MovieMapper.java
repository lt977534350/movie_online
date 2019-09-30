package com.woniu.orders.mapper.custom;

import com.woniu.orders.entity.Movie;

/**
 * @program: movie_online
 * @description:
 * @author: liutao
 * @create: 2019-09-29 09:27
 **/
public interface MovieMapper {
    Movie selectMovieByMovieShowTimeId(Integer msid);
}
