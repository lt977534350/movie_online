package com.woniu.api;

import com.woniu.entity.CinemaRoom;
import com.woniu.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/room")
public class CinemaRoomAPI {

    @PostMapping
    public Result insertRoom(CinemaRoom cinemaRoom) throws Exception{
        System.out.println(cinemaRoom);

        return null;
    }

}
