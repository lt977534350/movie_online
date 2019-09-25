package com.woniu.mapper;

import com.woniu.myutil.myeneity.Vip;

import java.util.List;

public interface VipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Vip record);

    int insertSelective(Vip record);

    Vip selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vip record);

    int updateByPrimaryKey(Vip record);

    List<Vip> selectVipByAid(Integer aid);

}