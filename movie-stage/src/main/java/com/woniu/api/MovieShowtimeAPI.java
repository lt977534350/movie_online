package com.woniu.api;

import com.woniu.entity.MovieShowtime;
import com.woniu.service.MovieShowtimeService;
import com.woniu.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/showtime")
public class MovieShowtimeAPI {

    @Resource
    private MovieShowtimeService movieShowtimeService;
    @GetMapping
    @RequestMapping("/select")
    public Result selectByCidAndMid(Integer cid,Integer mid)throws Exception{
        List<MovieShowtime> movieShowtimeList = movieShowtimeService.selectByCidAndMid(cid, mid);
        return new Result("success",null,null,movieShowtimeList);
    }


    @PostMapping
    @RequestMapping("/insert")
    public Result insertShowtime(MovieShowtime movieShowtime)throws Exception{
        System.out.println("请求进来了");
        System.out.println(movieShowtime);
        movieShowtimeService.insertOneData(movieShowtime);
        return new Result("success",null,null,null);
    }


    @PutMapping
    @RequestMapping("/update")
    public Result updateById(MovieShowtime movieShowtime)throws Exception{

        System.out.println(movieShowtime);
        movieShowtimeService.updateById(movieShowtime);


        return new Result("success",null,null,null);
    }


}
