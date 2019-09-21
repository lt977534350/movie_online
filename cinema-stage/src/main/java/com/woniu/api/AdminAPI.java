package com.woniu.api;

import com.woniu.entity.Admin;
import com.woniu.service.CinemaAdminService;
import com.woniu.util.Page;
import com.woniu.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminAPI {
    @Resource
    private CinemaAdminService cinemaAdminService;
    //获取电影院管理员的所有信息
    @GetMapping("all")
    public Result selectCinemaAdmins(Integer pageIndex){
        if(pageIndex==null||pageIndex==0){
            pageIndex = 1;
        }
        Integer num = 5;
        List<Admin> admins = cinemaAdminService.selectCinemaAdmins(pageIndex, num);
        Integer count = cinemaAdminService.count();
        Page page = new Page(pageIndex, count%num==0?count/num:count/num+1, count);
        System.out.println(admins);
        return new Result("success",null,page,admins);
    }
}
