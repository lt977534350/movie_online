package com.woniu.orders.service.serviceIpml;



import com.woniu.orders.mapper.custom.TasklistMapper;
import com.woniu.orders.service.TaskListService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: tickets-online
 * @description:
 * @author: liutao
 * @create: 2019-09-20 20:31
 **/

@Component
public class TaskListServiceImpl implements TaskListService {
    @Resource
    private TasklistMapper tasklistMapper;


    @Override
    public void deleteById(String id) throws Exception {
        tasklistMapper.deleteById(id);
    }
}
