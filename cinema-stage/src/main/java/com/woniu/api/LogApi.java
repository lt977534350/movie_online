package com.woniu.api;

import com.woniu.entity.Log;
import com.woniu.service.LogService;
import com.woniu.util.Page;
import com.woniu.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: movie_online
 * @description:
 * @author: liutao
 * @create: 2019-09-27 19:41
 **/
@RestController
@RequestMapping("log")
public class LogApi {
    @Resource
    private LogService logService;
    @GetMapping
    public Result selectLog(Integer pageIndex) throws Exception {
        if(pageIndex==null){
            pageIndex=1;
        }
        List<Log> logs = logService.selectLog(pageIndex);
        int i = logService.selectCount();
        Page page = new Page(pageIndex,i%10==0?i/10:i/10+1,i);
        return new Result("200",null,page,logs);
    }
}
