package com.woniu.jobs.mapper;


import com.woniu.jobs.entity.Seatinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeatinfoMapper {



    int updateStateToN(List<Seatinfo> list);

}