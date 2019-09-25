package com.woniu.orders.mapper.auto;

import com.woniu.orders.entity.VipPo;
import com.woniu.orders.entity.VipPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VipPoMapper {
    long countByExample(VipPoExample example);

    int deleteByExample(VipPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VipPo record);

    int insertSelective(VipPo record);

    List<VipPo> selectByExample(VipPoExample example);

    VipPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VipPo record, @Param("example") VipPoExample example);

    int updateByExample(@Param("record") VipPo record, @Param("example") VipPoExample example);

    int updateByPrimaryKeySelective(VipPo record);

    int updateByPrimaryKey(VipPo record);
}