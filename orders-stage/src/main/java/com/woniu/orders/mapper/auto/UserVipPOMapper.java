package com.woniu.orders.mapper.auto;

import com.woniu.orders.entity.UserVipPO;
import com.woniu.orders.entity.UserVipPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserVipPOMapper {
    long countByExample(UserVipPOExample example);

    int deleteByExample(UserVipPOExample example);

    int deleteByPrimaryKey(@Param("uid") Integer uid, @Param("aid") Integer aid);

    int insert(UserVipPO record);

    int insertSelective(UserVipPO record);

    List<UserVipPO> selectByExample(UserVipPOExample example);

    UserVipPO selectByPrimaryKey(@Param("uid") Integer uid, @Param("aid") Integer aid);

    int updateByExampleSelective(@Param("record") UserVipPO record, @Param("example") UserVipPOExample example);

    int updateByExample(@Param("record") UserVipPO record, @Param("example") UserVipPOExample example);

    int updateByPrimaryKeySelective(UserVipPO record);

    int updateByPrimaryKey(UserVipPO record);

    int deleteByPrimaryKey(Integer id);

    UserVipPO selectByPrimaryKey(Integer id);
}