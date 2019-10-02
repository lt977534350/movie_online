package com.woniu.jobs.service;






import com.woniu.jobs.entity.Seatinfo;

import java.util.List;

/**
 * @program: tickets-online
 * @description:
 * @author: liutao
 * @create: 2019-09-21 10:08
 **/
public interface SeatInfoService {


    int updateStateToN(List<Seatinfo> list);
}
