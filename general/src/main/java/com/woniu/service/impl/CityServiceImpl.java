package com.woniu.service.impl;

import com.woniu.entity.City;
import com.woniu.entity.CityExample;
import com.woniu.mapper.CityMapper;
import com.woniu.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    @Resource
    private CityMapper cityMapper;

    /**
     * 所有查询地区信息
     * @return
     * @throws Exception
     */
    @Override
    public List<City> getCitys() throws Exception {
        return cityMapper.getCitys();
    }

    /**
     * 根据父id查询所辖区域
     * @param pid
     * @return
     * @throws Exception
     */
    @Override
    public List<City> getCitysByCid(Integer pid) throws Exception {
        CityExample ce=new CityExample();
        ce.createCriteria().andPidEqualTo(pid);
        return cityMapper.selectByExample(ce);
    }
}
