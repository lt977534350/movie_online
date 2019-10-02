package com.woniu.orders.service;


import com.woniu.orders.entity.Seatinfo;


import java.util.List;

/**
 * @program: tickets-online
 * @description:
 * @author: liutao
 * @create: 2019-09-21 10:08
 **/
public interface SeatInfoService {
    List<Seatinfo> selectSeat(Integer mid ) throws  Exception;

    /**
     * 查询座位情况的方法
     * @param list
     * @return
     * @throws Exception
     */
    List<Seatinfo> selectStateByList(List<Seatinfo> list,Integer msid)throws  Exception;

    /**卖票更新座位的方法
     * @param list
     * @return
     * @throws Exception
     */
    int updateState(List<Seatinfo> list ) throws Exception;

    int updateStateToN(String seatid);
}
