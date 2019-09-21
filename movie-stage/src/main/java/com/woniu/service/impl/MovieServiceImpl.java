package com.woniu.service.impl;

import com.woniu.entity.Movie;
import com.woniu.mapper.MovieMapper;
import com.woniu.service.MovieService;
import com.woniu.util.Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {
    @Resource
    private MovieMapper movieMapper;
    @Override
    public Movie selectMovieByMid(Integer mid) throws Exception {
        Movie movie = movieMapper.selectMovieByMid(mid);
        return movie;
    }

    @Override
    public List<Movie> selectMoviesByName(String mName) throws Exception {
        List<Movie> movies = movieMapper.selectMoviesByName("%" + mName + "%");
        return movies;
    }

    @Override
    public List<Movie> selectMoviesByCid(Integer cid) throws Exception {
        List<Movie> movies = movieMapper.selectMoviesByCid(cid);
        return movies;
    }

    @Override
    public List<Movie> selectMoviesBytid(Integer tid, String comntry, Date uptime, Integer pageIndex, Integer num) throws Exception {
        return null;
    }

    @Override
    public Map<String, List<Movie>> selectMoviesOnByCid(Integer cid) throws Exception {
        //查询出当前影院所有已经拍片的所有电影
        List<Movie> movies = movieMapper.selectMOviesOnByCid(cid);

        //获取当前时间
        Date today = new Date();

        //储存正在上映的电影
        List<Movie> onShow = new ArrayList<>();
        //储存马上上映的电影
        List<Movie> willShow = new ArrayList<>();

        //将查询出的所有电影分类
        for(Movie m : movies){
            if(m.getUptime().before(today)){
                //上映日期在今天之前说明正在热映
                onShow.add(m);
            }else{
                //即将上映
                willShow.add(m);
            }
        }
        HashMap<String, List<Movie>> map = new HashMap<>();
        map.put("onShow",onShow);
        map.put("willShow",willShow);

        return map;
    }

    @Override
    public List<Movie> selectMovieListOrderByScore(Integer num) throws Exception {
        return movieMapper.selectMovieListByScore(num);
    }

    @Override
    public List<Movie> selectMovieListOrderByTime(Integer num) throws Exception {
        return movieMapper.selectMovieListByTime(num);
    }

    @Override
    public List<Movie> selectMovieListByExample(String type, String comntry, String time,Integer pageIndex) throws Exception {

        //渲染参数
        Map<String, Object> map = parseParams(type, comntry, time);


        //数据开始下标】
        Integer start = (pageIndex-1)* Util.NUM;

        List<Movie> movies = movieMapper.selectMovieListByExample((String) map.get("type"), "%" + map.get("comntry") + "%", (Date) map.get("startDate"), (Date)map.get("endTime"),start,Util.NUM);

        System.out.println(movies);

        return movies;

    }

    @Override
    public Long selectCountByExample(String type, String comntry, String time) throws Exception {
        Map<String, Object> map = parseParams(type, comntry, time);
        Long dataCount = movieMapper.selectCountByExample((String) map.get("type"), "%" + map.get("comntry") + "%", (Date) map.get("startDate"), (Date) map.get("endTime"));

        return dataCount;
    }

    /**
     * 渲染参数
     * @param type
     * @param comntry
     * @param time
     * @return
     * @throws Exception
     */
    public Map<String,Object> parseParams(String type, String comntry, String time) throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        //开始时间
        Date startDate=null;
        //结束时间
        Date endTime=null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //类型查全部类型
        if("".equals(type)){
            type=null;
        }

        //传入的上映日期不为空
        if(!"".equals(time)){

            //如果传入的时间格式为2000-2010
            if(time.contains("-")){
                startDate = simpleDateFormat.parse(time.split("-")[0] + "-01-01 00:00:00");
                endTime = simpleDateFormat.parse(Integer.valueOf(time.split("-")[1])+1 + "-01-01 00:00:00");
            }

            //如果传入的额时间为更早
            if("更早".equals(time)){
                startDate = simpleDateFormat.parse( "1970-01-01 00:00:00");
                endTime = simpleDateFormat.parse( "2000-01-01 00:00:00");
            }
            //传入正常的年份 eg：2019
            startDate = simpleDateFormat.parse(time + "-01-01 00:00:00");
            endTime = simpleDateFormat.parse(Integer.valueOf(time)+1 + "-01-01 00:00:00");
        }
        map.put("startDate",startDate);
        map.put("endTime",endTime);
        map.put("type",type);
        map.put("comntry",comntry);
        return map;

    }


}
