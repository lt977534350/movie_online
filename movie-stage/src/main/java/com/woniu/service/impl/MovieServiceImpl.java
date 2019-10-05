package com.woniu.service.impl;

import com.woniu.entity.Movie;
import com.woniu.entity.Type;
import com.woniu.mapper.MovieMapper;
import com.woniu.service.MovieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        List<Movie> movies = movieMapper.selectMovieListByScore(num);
        for (Movie movie:movies) {
            List<Type> types = movieMapper.selectTypeByMid(movie.getId());
            movie.setTypes(types);
        }
        return movies;
    }

    @Override
    public List<Movie> selectMovieListOrderByTime(Integer num) throws Exception {
        List<Movie> movies = movieMapper.selectMovieListByTime(num);
        for (Movie movie:movies) {
            int score = Double.valueOf(movie.getScore()).intValue();
            Integer star = score%2==0?score/2:score/2+1;
            movie.setStarnum(star);
            if(5-star<0){
                movie.setEmstarnum(0);
            }
            movie.setEmstarnum(5-star);
        }
        return movies;
    }

    @Override
    public List<Movie> selectAllMovies() throws Exception {
        return movieMapper.selectAllMovies();
    }

    @Override
    public Movie selectByPrimarykey(Integer mid) {
        return movieMapper.selectByPrimaryKey(mid);
    }

    @Override
    public List<Movie> selectByPage(Integer pageIndex, Integer num) {
        Integer start = (pageIndex-1)*num;
        HashMap<String, Integer> map = new HashMap<>();
        map.put("start",start);
        map.put("num",num);
        List<Movie> movies = movieMapper.selectByPage(map);
        return movies;
    }

    @Override
    public Integer count() {
        Integer count = movieMapper.count();
        return count;
    }

    @Override
    public void delMovie(Integer mid) {
        movieMapper.deleteByPrimaryKey(mid);
    }

    /**
     * 查询即将上映的电影
     * @param start
     * @param num
     * @return
     */
    @Override
    public List<Movie> selectAfterMovies(Integer start, Integer num,Date today) {
        return movieMapper.selectAfterMovies(start,num,today);
    }

    @Override
    public Integer selectAfterCount(Date today) {
        return movieMapper.selectAfterCount(today);
    }

    @Override
    public List<Movie> selectMoviesByExample(String type, String comntry, Date startTime, Date endTime, Integer start, Integer num) {
        List<Movie> movies=movieMapper.selectMoviesByExample(type,"%"+comntry+"%",startTime,endTime,start,num);
        return movies;
    }

    @Override
    public Integer selectCountByExample(String type, String comntry, Date startTime, Date endTime) {
        return movieMapper.selectCountByExample(type,"%"+comntry+"%",startTime,endTime);
    }

}
