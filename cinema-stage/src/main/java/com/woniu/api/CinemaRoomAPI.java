package com.woniu.api;

import com.woniu.entity.CinemaRoom;
import com.woniu.service.CinemaRoomService;
import com.woniu.util.Page;
import com.woniu.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/room")
public class CinemaRoomAPI {

    @Resource
    private CinemaRoomService cinemaRoomService;
    @PostMapping
    public Result insertRoom(CinemaRoom cinemaRoom) throws Exception{
        System.out.println(cinemaRoom);
        cinemaRoomService.insertRoom(cinemaRoom);
        return new Result("success","添加影厅成功",null,null);
    }
    @PutMapping
    public Result updateRoom(CinemaRoom cinemaRoom) throws Exception{
        System.out.println(cinemaRoom);
        cinemaRoomService.updateById(cinemaRoom);

        return new Result("success","修改影厅成功",null,null);
    }


    @GetMapping
    @RequestMapping("/bycid")
    public Result selectAllByCid(Integer cid,Integer pageIndex,Integer num)throws Exception{

        if(pageIndex == null && num == null){
            List<CinemaRoom> list = cinemaRoomService.selectAllByCid(cid,null,null);
            return new Result("success",null,null,list);
        }


        //查询cid放映点下的影厅数量
        Integer dataCount = cinemaRoomService.selectCountByCid(cid);
        Integer pageCount=dataCount%num==0?dataCount/num:dataCount/num+1;

        Page page=new Page(pageIndex,pageCount,dataCount);

        //查询第一页数据
        Integer start = (pageIndex-1)*num;
        List<CinemaRoom> list = cinemaRoomService.selectAllByCid(cid,start,num);
        return new Result("success",null,page,list);
    }


}
