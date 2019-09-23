package com.woniu.service;

import com.woniu.entity.CinemaRoom;

public interface CinemaRoomService {

    /**
     * 插入一条放映厅信息
     * @param cinemaRoom
     * @throws Exception
     */
    void insertRoom(CinemaRoom cinemaRoom)throws Exception;
}
