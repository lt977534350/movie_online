package com.woniu.serviceimpl;


import com.woniu.entity.Log;
import com.woniu.entity.LogExample;
import com.woniu.mapper.LogMapper;
import com.woniu.service.LogService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<Log> selectLog(Integer padeIndex) throws Exception {
        LogExample logExample = new LogExample();
        logExample.setStart((padeIndex-1)*10);
        logExample.setNum(10);
        logExample.setOrderByClause("id desc");
        List<Log> logs = logMapper.selectByExample(logExample);
        return logs;
    }

    @Override
    public int selectCount() throws Exception {
        return (int)logMapper.countByExample(null);
    }
}
