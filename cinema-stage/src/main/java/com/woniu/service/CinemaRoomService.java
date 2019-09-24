package com.woniu.service;

import com.woniu.entity.CinemaRoom;

import java.util.List;

public interface CinemaRoomService {

    /**
     * 插入一条放映厅信息
     * @param cinemaRoom
     * @throws Exception
     */
    void insertRoom(CinemaRoom cinemaRoom)throws Exception;

    /**
     * 根据cid查询所有的放映厅信息
     * @param cid
     * @return
     */
    List<CinemaRoom> selectAllByCid(Integer cid) throws Exception;
}
