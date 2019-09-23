package com.woniu.service;


import com.woniu.entity.Admin;

import java.util.List;
import java.util.Map;

public interface CinemaAdminService {
    //查出所有的影院管理员信息,将即将到期的管理员账号封装好
    public List<Admin> selectCinemaAdmins(Integer pageIndex, Integer num);
    //查询影院管理员总数
    public Integer count();
    //根据用户名查询管理员
    public Admin login(String username);
    //根据电话号码查询管理员
    public Admin selectByPhone(String phone);
    //将影院管理员注册信息写入数据库
    public void insert(Admin admin);

}
