package com.woniu.orders.mapper;

import com.woniu.orders.entity.Seatinfo;
import com.woniu.orders.entity.SeatinfoExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SeatinfoMapper {
    long countByExample(SeatinfoExample example);

    int deleteByExample(SeatinfoExample example);

    int insert(Seatinfo record);

    int insertSelective(Seatinfo record);

    List<Seatinfo> selectByExample(SeatinfoExample example);

    Seatinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Seatinfo record, @Param("example") SeatinfoExample example);

    int updateByExample(@Param("record") Seatinfo record, @Param("example") SeatinfoExample example);

    int updateByPrimaryKeySelective(Seatinfo record);

    int updateByPrimaryKey(Seatinfo record);

    List<Seatinfo> selectSeat(@Param("msid") Integer msid);
    //根据list 对象查询订单状态
    List<Seatinfo> selectStateByList(List<Seatinfo> list);

    int updateState( List<Seatinfo> list);
    int updateStateToN(List<Seatinfo> list);

}