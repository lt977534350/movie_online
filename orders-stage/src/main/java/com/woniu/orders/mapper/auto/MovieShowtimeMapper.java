package com.woniu.orders.mapper.auto;

import com.woniu.orders.entity.MovieShowInfo;
import org.omg.PortableInterceptor.INACTIVE;

public interface MovieShowtimeMapper {
    //根据id查询票价
    Double selectPrice(Integer msid);

    MovieShowInfo selectMovieShowInfo(Integer msid);

    int selectAid (Integer msid);


}