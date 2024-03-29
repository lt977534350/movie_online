package com.woniu.api;

import com.woniu.entity.Movie;
import com.woniu.entity.MoviePerson;
import com.woniu.service.MoviePersonService;
import com.woniu.service.MovieService;
import com.woniu.util.Page;
import com.woniu.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("movie")
public class MovieAPI {
    @Resource
    private MoviePersonService moviePersonService;
    @Resource
    private MovieService movieService;
    @GetMapping
    @RequestMapping("/bymid/{mid}")
    public Result selectMovieByMid(@PathVariable Integer mid) throws Exception{
        Movie movie = movieService.selectMovieByMid(mid);
        MoviePerson moviePerson = moviePersonService.selectByMid(mid);
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("movie",movie);
        map.put("moviePerson",moviePerson);

        return new Result("success",null,map,null);
    }

    /**
     * 查询所有的电影num个，按照上映时间排序
     * @param num 查询数量
     * @return
     * @throws Exception
     */
    @GetMapping
    @RequestMapping("/orderbytime/{num}")
    public Result selectMovieListOrderByTime(@PathVariable Integer num)throws Exception{
        List<Movie> movies = movieService.selectMovieListOrderByTime(num);

        return new Result("success",null,null,movies);
    }

    /**
     * 查询所有的电影num个通过评分排序
     * @param num
     * @return
     * @throws Exception
     */
    @GetMapping
    @RequestMapping("/orderbyscore/{num}")
    public Result selectMovieListOrderByScore(@PathVariable Integer num)throws Exception{
        List<Movie> movies = movieService.selectMovieListOrderByScore(num);

        return new Result("success",null,null,movies);
    }


    @GetMapping
    @RequestMapping("/bycid/{cid}")
    public Result selectMoviesByCid(@PathVariable Integer cid) throws Exception{
        return new Result("success",null,null,movieService.selectMoviesByCid(cid));
    }

    @GetMapping
    @RequestMapping("/byname/{mName}")
    public Result selectMoviesByName(@PathVariable String mName) throws Exception{
        return new Result("succsee",null,null,movieService.selectMoviesByName(mName));
    }

    @GetMapping
    @RequestMapping("/movieon/{cid}")
    public Result selectMoviesOnByCid(@PathVariable Integer cid) throws Exception{
        Map<String, List<Movie>> MoviesMap = movieService.selectMoviesOnByCid(cid);
        return new Result("success",null,MoviesMap,null);
    }

    /**
     * 查询所有的电影信息
     * @return
     * @throws Exception
     */
    @GetMapping
    @RequestMapping("/all")
    public Result selectAllMovies()throws Exception{
        List<Movie> movies = movieService.selectAllMovies();
        return new Result("success",null,null,movies);
    }

    @GetMapping
    @RequestMapping("/byid")
    public Result selectByPimarykey(Integer mid)throws Exception{
        Movie movie=movieService.selectByPrimarykey(mid);
        return new Result("success",null,movie,null);
    }

    @GetMapping("movies")
    public Result selectByPage(Integer pageIndex,Integer num) throws Exception{
        if(num==null){
            num=3;
        }

        if(pageIndex==null||pageIndex.equals("")){
            pageIndex = 1;
        }
        List<Movie> movies = movieService.selectByPage(pageIndex, num);
        Integer movieCount = movieService.count();
        Page page = new Page();
        page.setDataCount(movieCount);
        page.setPageIndex(pageIndex);
        page.setPageCount(movieCount%num==0?movieCount/num:movieCount/num+1);
        return new Result("success","查询成功！",page,movies);
    }

    @DeleteMapping("delete")
    public Result delMovie(Integer mid) throws Exception{
        movieService.delMovie(mid);
        return new Result("success","删除成功！",null,null);
    }


    //查询即将上映的电影
    @GetMapping
    @RequestMapping("/aftertoday")
    public Result selectAfterMovies(Integer pageIndex)throws Exception{
        Integer num=5;
        //获取当前时间
        Date today = new Date();

        List<Movie> list=movieService.selectAfterMovies((pageIndex-1)*num,num,today);
        Integer count=movieService.selectAfterCount(today);
        Integer pageCount=count%num==0?count/num:count/num+1;
        Page page = new Page(pageIndex, pageCount, count);
        return new Result("success",null,page,list);
    }


    /**
     * 根据条件查询电影和数量
     * @param type
     * @param comntry
     * @param time
     * @param pageIndex
     * @return
     * @throws Exception
     */
    @GetMapping
    @RequestMapping("/example")
    public Result selectMoviesByExample(String type,String comntry,String time,Integer pageIndex)throws Exception{
        System.out.println(type+"="+comntry+"="+time+"="+pageIndex);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime=null;
        Date endTime=null;

        if(type == ""){
            type=null;
        }

        if(time !=null && time !=""){
            if(time.contains("-")){
                String[] times = time.split("-");
                startTime=simpleDateFormat.parse(times[0]+"-1-1 00:00:00");
                endTime=simpleDateFormat.parse(Integer.valueOf(times[1])+1+"-1-1 00:00:00");
            }else if(time.contains("更早")){
                startTime=simpleDateFormat.parse("1970-1-1 00:00:00");
                endTime=simpleDateFormat.parse("2000-1-1 00:00:00");


            }else{

                startTime=simpleDateFormat.parse(time+"-1-1 00:00:00");
                endTime=simpleDateFormat.parse(Integer.valueOf(time)+1+"-1-1 00:00:00");
            }


        }



        Integer num=18;
        Integer start = (pageIndex-1)* num;

        List<Movie> movies = movieService.selectMoviesByExample(type, comntry, startTime, endTime, start, num);

        Integer dataCount = movieService.selectCountByExample(type, comntry, startTime, endTime);
        Integer pageCount =  dataCount%num == 0 ? dataCount/num : dataCount/num+1;

        Page page = new Page(pageIndex,pageCount,dataCount);


        return new Result("success",null,page,movies);
    }


}
