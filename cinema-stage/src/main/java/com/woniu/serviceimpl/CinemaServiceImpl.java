package com.woniu.serviceimpl;

import com.woniu.entity.Cinema;
import com.woniu.mapper.CinemaMapper;
import com.woniu.service.CinemaService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class CinemaServiceImpl implements CinemaService {
    @Resource
    private CinemaMapper cinemaMapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    @Cacheable
    public List<Cinema> selectByAid(Integer pageIndex, Integer num, Integer aid) {
        Integer start = (pageIndex-1)*num;
        HashMap<String, Integer> map = new HashMap<>();
        map.put("start",start);
        map.put("num",num);
        map.put("aid",aid);
        List<Cinema> cinemas = cinemaMapper.selectByAid(map);
        ValueOperations<String,Object> operations = redisTemplate.opsForValue();
        operations.set("cinemas",cinemas);
        return cinemas;
    }
    @Override
//    @Cacheable
    public Cinema selectById(Integer cid) {
        Cinema cinema = cinemaMapper.selectByPrimaryKey(cid);
        System.out.println(cinema);
        return cinema;
    }

    @Override
    public Integer count() {
        Integer count = cinemaMapper.count();
        return count;
    }

    @Override
    public Integer insert(Cinema cinema) {
        cinemaMapper.insert(cinema);
        return null;
    }

    @Override
    public Integer delete(Integer cid) {
        int row = cinemaMapper.deleteByPrimaryKey(cid);
        return row;
    }

    @Override
    public Integer update(Cinema cinema) {
        int row = cinemaMapper.updateByPrimaryKeySelective(cinema);
        return row;
    }

    @Override
    public List<Cinema> selectByCity(Integer ctid, Integer pageIndex, Integer num) {
        Integer start = (pageIndex-1)*num;
        HashMap<String, Integer> map = new HashMap<>();
        map.put("start",start);
        map.put("num",num);
        map.put("ctid",ctid);
        List<Cinema> cinemas = cinemaMapper.selectByCity(map);
        return cinemas;
    }
}
