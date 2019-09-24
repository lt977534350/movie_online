package com.woniu.orders.service;

import com.woniu.orders.entity.VipPo;

import java.util.List;

/**
 * @program: movie_online
 * @description:
 * @author: liutao
 * @create: 2019-09-24 14:39
 **/
public interface VipService {
    List<VipPo> selectVipByAid(Integer aid);
}
