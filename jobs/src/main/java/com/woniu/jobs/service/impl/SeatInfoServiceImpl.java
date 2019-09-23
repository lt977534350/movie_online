package com.woniu.jobs.service.impl;


import com.woniu.jobs.entity.Seatinfo;
import com.woniu.jobs.mapper.SeatinfoMapper;
import com.woniu.jobs.service.SeatInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public int updateStateToN(List<Seatinfo> list) {
        return  seatinfoMapper.updateStateToN(list);
    }

}
