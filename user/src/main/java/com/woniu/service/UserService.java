package com.woniu.service;

import com.woniu.entity.User;

public interface UserService {
    /*根据用户id查询用户信息；*/
    User getUser(Integer Uid)throws Exception;
    /*更新用户信息*/
    void updateUser(User user)throws Exception;
    /*删除用户信息*/
    void deleteUser(Integer uid)throws Exception;
    /*新增用户信息*/
    void insertUser(User user)throws Exception;
    /*根据用户名查询信息*/
    User selectByName(String username) throws Exception;
    /*根据手机号查询用户*/
    User selectByPhone(String phonenum) throws Exception;

}
