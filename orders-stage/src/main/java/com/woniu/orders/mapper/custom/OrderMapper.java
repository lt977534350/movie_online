package com.woniu.orders.mapper.custom;

import com.woniu.orders.entity.Order;
import com.woniu.orders.util.CountDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    List<Order> selectOrderByUid(@Param("uid") int uid, @Param("start") int start, @Param("num") int num);

    Order selectDetail(String oid);

    List<Order> selectOrder(Map<String, Integer> map);

    List<CountDetail> selectOrderSuccess();
    List<CountDetail> selectOrderRefund();

    int updateIsDel(String oid);

}