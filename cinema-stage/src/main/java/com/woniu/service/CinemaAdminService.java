package com.woniu.service;


import com.woniu.entity.Admin;

import java.util.List;
import java.util.Map;

public interface CinemaAdminService {
    //查出所有的影院管理员信息,将即将到期的管理员账号封装好
    public List<Admin> selectCinemaAdmins(Integer pageIndex, Integer num);
    //查询影院管理员总数
    public Integer count();

}
