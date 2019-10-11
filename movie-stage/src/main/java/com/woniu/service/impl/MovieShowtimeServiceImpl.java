package com.woniu.service.impl;

import com.woniu.entity.CinemaRoom;
import com.woniu.entity.MovieShowtime;
import com.woniu.entity.Seatinfo;
import com.woniu.mapper.CinemaRoomMapper;
import com.woniu.mapper.MovieShowtimeMapper;
import com.woniu.mapper.SeatinfoMapper;
import com.woniu.service.MovieShowtimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MovieShowtimeServiceImpl implements MovieShowtimeService {
    @Resource
    private MovieShowtimeMapper movieShowtimeMapper;
    @Resource
    private CinemaRoomMapper cinemaRoomMapper;
    @Resource
    private SeatinfoMapper seatinfoMapper;

    @Override
    public List<MovieShowtime> selectByCidAndMid(Integer cid,Integer mid) throws Exception {
        return movieShowtimeMapper.selectByCidAndMid(cid,mid);
    }

    /**
     * 插入一条排片信息
     * @param movieShowtime
     */
    @Override
    public void insertOneData(MovieShowtime movieShowtime) {
        //拍片id
        movieShowtimeMapper.insertSelective(movieShowtime);
        Integer msid = movieShowtime.getId();
        //放映厅ID
        Integer roomId=movieShowtime.getRoomId();
        //根据roomId查询影厅的座位信息
        CinemaRoom cinemaRoom = cinemaRoomMapper.selectByPrimaryKey(roomId);
        //得到coordinate
        String coordinate = cinemaRoom.getCoordinate();
        //遍历coordinate向seatinfo插入数据

        //将coordinate字符串转换成数组
        String[] seats = coordinate.split(",");

        Seatinfo info = null;

        //遍历数组，向seatinfo表里面插入数据
        for(int i = 0; i<seats.length; i++){
            for(int j=0; j<seats[i].length();j++){
                //当前字符seats[i].charAt(j);
                if("e".equals(seats[i].charAt(j)+"")){
                    info = new Seatinfo(null,1+i+"",1+j+"","N",msid);
                }else{
                    info = new Seatinfo(null,1+i+"",1+j+"","E",msid);
                }
                //向数据库插入
                int insert = seatinfoMapper.insert(info);

            }
        }

        //row 行
        //column 列
        //state 状态 N未选择
    }

    @Override
    public void updateById(MovieShowtime movieShowtime) {
        movieShowtimeMapper.updateByPrimaryKey(movieShowtime);
    }

    @Override
    public void deleteById(Integer id) {
        //删除排片表里的数据
        movieShowtimeMapper.deleteByPrimaryKey(id);

        //删除Seatinfo表里的数据
        seatinfoMapper.deleteByMsid(id);


    }
}
