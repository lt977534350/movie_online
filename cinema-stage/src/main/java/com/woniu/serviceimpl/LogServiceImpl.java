package com.woniu.serviceimpl;


import com.woniu.entity.Log;
import com.woniu.mapper.LogMapper;
import com.woniu.service.LogService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: movie_online
 * @description:
 * @author: liutao
 * @create: 2019-09-27 11:02
 **/
@Service
public class LogServiceImpl implements LogService {
    @Resource
    private LogMapper logMapper;
    @Override
    public int insertLog(Log log) {
        int i = logMapper.insertSelective(log);
        return i;
    }
}
