package com.woniu.orders.service;


import com.woniu.orders.entity.UserVipPO;
import com.woniu.orders.entity.Vip;
import com.woniu.orders.entity.VipPo;

import java.util.List;

/**
 * @program: movie_online
 * @description:
 * @author: liutao
 * @create: 2019-09-24 10:43
 **/
public interface UserVipService {
     int getVipId(List<VipPo> vipList, Double consumption)throws Exception;

     List<UserVipPO> select(Integer uid, Integer aid);

}
