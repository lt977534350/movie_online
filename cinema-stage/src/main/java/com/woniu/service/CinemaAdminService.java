package com.woniu.service;


import com.woniu.myutil.myeneity.Vip;
import com.woniu.myutil.myeneity.Admin;

import java.util.List;

public interface CinemaAdminService {
    //查出所有的影院管理员信息,将即将到期的管理员账号封装好
    public List<Admin> selectCinemaAdmins(Integer pageIndex, Integer num) throws Exception;
    //查询影院管理员总数
    public Integer count() throws Exception;
    //根据用户名查询管理员
    public Admin login(String username) throws Exception;
    //根据电话号码查询管理员
    public Admin selectByPhone(String phone) throws Exception;
    //将影院管理员注册信息写入数据库
    public void insert(Admin admin) throws Exception;
    //更新平台管理员信息
    public void update(Admin admin) throws Exception;
    //根据aid查询出管理员信息
    public Admin selectByAid(Integer aid) throws Exception;
    //根据aid查询出影院的vip设置
    public List<Vip> selectVipByAid(Integer aid) throws Exception;
    //根据vip的id更改vip配置
    public void updateVip(Vip vip) throws Exception;
    //新增vip设置
    void insertVipByAid(Vip vip);
    //根据名字模糊查询
    Admin selectAdminByShortName(String shortName);

    void updateLogo(Integer id, String logo);

    void insertPic(Integer id, String picName);
}
