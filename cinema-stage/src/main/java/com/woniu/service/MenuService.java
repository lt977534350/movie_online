package com.woniu.service;

import com.woniu.myutil.myeneity.Menu;

import java.util.List;

public interface MenuService {
    //查询所有的套餐
    public List<Menu> selectMenus() throws Exception;
    //根据ID删除一份套餐
    public Integer deleteById(Integer mid) throws Exception;
    //新增一份套餐
    public Integer insert(Menu menu) throws Exception;
    //根据id修改一份套餐
    public Integer updateById(Menu menu) throws Exception;
    //根据影院管理员id查出其对应的套餐
    public Menu selectByAid(Integer aid) throws Exception;
}
