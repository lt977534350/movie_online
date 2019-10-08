package com.woniu.api;

import com.woniu.entity.Photo;
import com.woniu.service.PhotoService;
import com.woniu.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoApi {

    @Resource
    private PhotoService photoService;

    @GetMapping
    @RequestMapping("/byaid")
    public Result selectPicsByAid(Integer aid)throws Exception{
        List<Photo> photoList = photoService.selectPicByAid(aid);
        System.out.println(photoList);
        return new Result("success","查询成功",null,photoList);
    }


    @DeleteMapping
    @RequestMapping("/deletebyid")
    public Result deleteById(Integer id)throws Exception{
        photoService.deleteById(id);
        return new Result("success","删除成功",null,null);
    }



}
