package com.woniu.orders.mapper.custom;


import com.woniu.orders.entity.UserVip;
import org.apache.ibatis.annotations.Param;

public interface UserVipMapper {
    int updateUserVip(UserVip userVip);


    UserVip selectConsume(@Param("uid") Integer uid, @Param("aid")Integer aid );
}
