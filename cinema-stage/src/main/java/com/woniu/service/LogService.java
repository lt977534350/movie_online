package com.woniu.service;

import com.woniu.entity.Log;

import java.util.List;

public interface LogService {

    int insertLog(Log log);

    /**
     * 分页查询日志
     * @param padeIndex
     * @return
     * @throws Exception
     */
    List<Log> selectLog (Integer padeIndex)throws Exception;

    int selectCount()throws  Exception;
}
