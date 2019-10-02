package com.woniu.mapper;


import java.util.List;

import com.woniu.jobs.entity.Orders;
import com.woniu.jobs.entity.OrdersPO;
import com.woniu.jobs.entity.OrdersPOExample;
import org.apache.ibatis.annotations.Param;

public interface OrdersPOMapper {
    long countByExample(OrdersPOExample example);

    int deleteByExample(OrdersPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrdersPO record);

    int insertSelective(OrdersPO record);

    List<OrdersPO> selectByExample(OrdersPOExample example);

    OrdersPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrdersPO record, @Param("example") OrdersPOExample example);

    int updateByExample(@Param("record") OrdersPO record, @Param("example") OrdersPOExample example);

    int updateByPrimaryKeySelective(OrdersPO record);

    int updateByPrimaryKey(OrdersPO record);

    int updateOstate(String oid);

    Orders selectSeatIdByOid(String Oid);
}