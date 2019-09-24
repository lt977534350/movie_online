package com.woniu.orders.mapper.custom;



import com.woniu.orders.entity.Tasklist;

import java.util.List;

public interface TasklistMapper {

    //查询付款超时任务

    public void deleteById(String id);
}