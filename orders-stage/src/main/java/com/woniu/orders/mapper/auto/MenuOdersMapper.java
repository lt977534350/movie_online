package com.woniu.orders.mapper.auto;

import com.woniu.orders.entity.MenuOders;
import com.woniu.orders.entity.MenuOdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuOdersMapper {
    long countByExample(MenuOdersExample example);

    int deleteByExample(MenuOdersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MenuOders record);

    int insertSelective(MenuOders record);

    List<MenuOders> selectByExample(MenuOdersExample example);

    MenuOders selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MenuOders record, @Param("example") MenuOdersExample example);

    int updateByExample(@Param("record") MenuOders record, @Param("example") MenuOdersExample example);

    int updateByPrimaryKeySelective(MenuOders record);

    int updateByPrimaryKey(MenuOders record);
}