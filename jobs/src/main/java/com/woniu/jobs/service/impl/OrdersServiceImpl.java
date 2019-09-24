package com.woniu.jobs.service.impl;

import com.woniu.jobs.entity.Orders;
import com.woniu.jobs.entity.OrdersExample;
import com.woniu.jobs.entity.Seatinfo;
import com.woniu.jobs.mapper.OrdersMapper;
import com.woniu.jobs.service.OrderService;
import com.woniu.jobs.service.SeatInfoService;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @program: tickets-online
 * @description:
 * @author: liutao
 * @create: 2019-09-20 20:21
 **/
@Component
public class OrdersServiceImpl implements OrderService {
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private SeatInfoService seatInfoService;

    @Override
    public int updateOstate(String oid) throws Exception {


        Orders ordersInfo = ordersMapper.selectSeatIdByOid(oid);
        String seat = ordersInfo.getSeatId();
        String[] split = seat.split("-");
        List<Seatinfo> seatinfoList = new ArrayList<>();
        //如果订单未付款，则还原座位信息
        if (ordersInfo.getOstate() == 10) {
            System.out.println("还原座位表信息");
            for (int i = 0; i < split.length; i++) {
                Seatinfo seatinfo = new Seatinfo();
                seatinfo.setId(Integer.parseInt(split[i]));
                seatinfoList.add(seatinfo);
            }
            //还原座位表信息
            int i = seatInfoService.updateStateToN(seatinfoList);
        }
        Orders orders = new Orders();
        orders.setOstate((byte) 0);
        OrdersExample ordersExample = new OrdersExample();
        OrdersExample.Criteria criteria = ordersExample.createCriteria();
        criteria.andOrderIdEqualTo(oid);
        criteria.andOstateEqualTo((byte) 10);
        return ordersMapper.updateByExampleSelective(orders, ordersExample);

    }
}
