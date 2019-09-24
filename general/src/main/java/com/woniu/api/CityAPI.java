package com.woniu.api;

import com.woniu.service.CityService;
import com.woniu.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("city")
public class CityAPI {
    @Resource
    private CityService cityService;

    /**
     * 查询所有地区（前25个）；
     * @return
     * @throws Exception
     */
    @GetMapping
    public Result getCitys()throws Exception{
        return new Result("success",null,null,cityService.getCitys());
    }

    /**
     * 根据pid查询地区信息
     * @param pid
     * @return
     * @throws Exception
     */
    @GetMapping("/{pid}")
    public Result getCitysByCid(@PathVariable Integer pid)throws Exception{
        return new Result("success",null,null,cityService.getCitysByCid(pid));
    }
}
