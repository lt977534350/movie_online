package com.woniu.api;

import com.woniu.entity.Cinema;
import com.woniu.service.CinemaService;
import com.woniu.util.Page;
import com.woniu.util.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cinema")
public class CinemaAPI {
    @Resource
    private CinemaService cinemaService;
    @GetMapping
    @RequestMapping("byaid")
    public Result selectCinemas(Integer pageIndex, Integer aid){
        if(pageIndex==null||pageIndex==0){
            pageIndex = 1;
        }
        Integer num = 4;
        List<Cinema> cinemas = cinemaService.selectByAid(pageIndex,num,aid);
        System.out.println(cinemas);
        Integer count = cinemaService.count();
        Page page = new Page(pageIndex, count%num==0?count/num:count/num+1, count);
        List<String> msgs = cinemaService.getTime(aid);
        System.out.println("过期信息------"+msgs.get(1));
        return new Result(msgs.get(1),msgs.get(0),page,cinemas);
    }
    @GetMapping("bycid")
    public Result selectCinema(Integer cid){
        Cinema cinema = cinemaService.selectById(cid);
        System.out.println(cinema);
        return new Result("success",null,cinema,null);
    }
    @PostMapping("insert")
    public Result insertCinema(MultipartFile file, String c_name, String c_address,
                               String copy_right, Integer aid, String phone, String city, HttpServletRequest req){
        String path = req.getSession().getServletContext().getRealPath("/");
        String imgPath = path.split("\\\\")[0]+"\\projectImg\\";
        File mk = new File(imgPath);
        if(!mk.exists()){
            mk.mkdirs();
        }
        //图片扩展名
        String filename = file.getOriginalFilename();
        System.out.println(mk);
        String newPicName = UUID.randomUUID()+filename;
        //新的图片文件路径
        File pic = new File(mk, newPicName);
        try{
            //将上传的图片存入新的文件中
            file.transferTo(pic);
        }catch (Exception e){
            e.printStackTrace();
        }
        Cinema cinema = new Cinema();
        cinema.setCName(c_name);
        cinema.setCAddress(c_address);
        cinema.setCopyRight(copy_right);
        cinema.setFacility(mk+newPicName);
        cinema.setAid(aid);
        cinema.setPhone(phone);
        cinema.setCity(city);
        cinemaService.insert(cinema);
        return new Result("success",null,null,null);
    }
    @DeleteMapping
    public Result deleteCinema(Integer cid){
        Integer row = cinemaService.delete(cid);
        return new Result("success",null,null,null);
    }
    //未完成
    @PutMapping
    public Result updateCinema(){
        Cinema cinema = new Cinema();
        Integer row = cinemaService.update(cinema);
        return new Result("success",null,null,null);
    }

    /**
     * 根据城市名称查询cinema信息
     * @param city
     * @param pageIndex
     * @return
     */
    @GetMapping("/{city}")
    public Result selectByCity(@PathVariable("city") String city,Integer pageIndex)throws Exception{
        if(pageIndex==null){
            pageIndex = 1;
        }
        /*设置每页显示条数*/
        Integer num = 5;
        /*查询数据总条数*/
        int countNumByCity = cinemaService.getCountNumByCity(city);
        /*计算总页数*/
        int pageCount=countNumByCity%num==0?countNumByCity/num:countNumByCity/num+1;
        /*封装page对象*/
        Page page=new Page(pageIndex,pageCount,countNumByCity);
        List<Cinema> cinemas = cinemaService.selectByCity(city, pageIndex, num);
        return new Result("success",null,page,cinemas);
    }

    /**
     * 条件查询影院信息;
     * @param cinema
     * @param city
     * @param cinemaHall
     * @return
     * @throws Exception
     */
    @GetMapping("cinemas")
    public Result getCinemas(String cinema,String city,String cinemaHall,Integer pageIndex)throws Exception{
        if(pageIndex==null){
            pageIndex=1;
        }
        /*设置每页显示5条影院数据*/
        int num=5;
        /*查询数据总条数*/
        int countNum = cinemaService.getCountNum(cinema, city, cinemaHall);
        /*计算总页数*/
        int pageCount=1;
        if(countNum>num){
            pageCount=countNum%num==0?countNum/num:countNum/num+1;
        }
        /*封装pge对象；*/
        Page page=new Page(pageIndex,countNum,pageCount);
        /*调用方法执行业务*/
        return new Result("success",null,page,cinemaService.getCinemas(cinema,city,cinemaHall, num, pageIndex));
    }

    /**
     * 根据aid查询所有的影院
     * @return
     * @throws Exception
     */
    @GetMapping
    @RequestMapping("/all")
    public Result selectAllCinemaByAid(Integer aid)throws Exception{
        List<Cinema> cinemas = cinemaService.selectAllByAid(aid);
        return new Result("success",null,null,cinemas);
    }



}
