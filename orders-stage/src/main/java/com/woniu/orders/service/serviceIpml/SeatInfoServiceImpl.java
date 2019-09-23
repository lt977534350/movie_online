package com.woniu.orders.service.serviceIpml;

import com.woniu.orders.entity.Seatinfo;
import com.woniu.orders.mapper.SeatinfoMapper;
import com.woniu.orders.service.SeatInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: tickets-online
 * @description:
 * @author: liutao
 * @create: 2019-09-21 10:11
 **/
@Service
public  class SeatInfoServiceImpl implements SeatInfoService {
    @Resource
    private SeatinfoMapper seatinfoMapper;
    @Override
    public List<Seatinfo> selectSeat(Integer msid ) throws Exception {
           return seatinfoMapper.selectSeat(1);
    }

    @Override
    public List<Seatinfo> selectStateByList(List<Seatinfo> list,Integer msid ) throws Exception {

           return seatinfoMapper.selectStateByList(list);
    }

    @Override
    public int updateState(List<Seatinfo> list) throws Exception {
        return  seatinfoMapper.updateState(list);
    }

    @Override
    public int updateStateToN(List<Seatinfo> list) {
        return  seatinfoMapper.updateStateToN(list);
    }

}
