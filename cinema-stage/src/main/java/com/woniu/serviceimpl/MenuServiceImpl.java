package com.woniu.serviceimpl;

import com.woniu.mapper.MenuMapper;
import com.woniu.myutil.myeneity.Menu;
import com.woniu.service.MenuService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;
    @Override
    @Cacheable
    public List<Menu> selectMenus() throws Exception {
        List<Menu> menus = menuMapper.selectAll();
        return menus;
    }

    @Override
    public Integer deleteById(Integer mid) throws Exception {
        int row = menuMapper.deleteByPrimaryKey(mid);
        return row;
    }

    @Override
    public Integer insert(Menu menu) throws Exception {
        int row = menuMapper.insertSelective(menu);
        return row;
    }

    @Override
    public Integer updateById(Menu menu) throws Exception {
        int row = menuMapper.updateByPrimaryKeySelective(menu);
        return row;
    }

    @Override
    public Menu selectByAid(Integer aid) throws Exception {
        Menu menu = menuMapper.selectByAid(aid);
        return menu;
    }
}
