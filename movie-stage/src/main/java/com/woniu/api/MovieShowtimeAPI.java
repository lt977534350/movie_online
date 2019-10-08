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
        movieShowtimeService.insertOneData(movieShowtime);
        return new Result("success",null,null,null);
    }


    @PutMapping
    @RequestMapping("/update")
    public Result updateById(MovieShowtime movieShowtime)throws Exception{
        movieShowtimeService.updateById(movieShowtime);
        return new Result("success",null,null,null);
    }


    @DeleteMapping
    @RequestMapping("/deletebyid")
    public Result deleteById(Integer id) throws Exception{
        movieShowtimeService.deleteById(id);
        return new Result("success","删除成功",null,null);
    }

}
