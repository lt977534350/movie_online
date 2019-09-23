package com.woniu.mapper;

import com.woniu.entity.Admin;

import java.util.HashMap;
import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> selectCinemaAdmins(HashMap<String, Integer> map);

    Integer count();

    Admin login(String username);

    Admin selectByPhone(String phone);
}