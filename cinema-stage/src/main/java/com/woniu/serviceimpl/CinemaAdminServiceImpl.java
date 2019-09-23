package com.woniu.serviceimpl;

import com.woniu.entity.Admin;
import com.woniu.mapper.AdminMapper;
import com.woniu.service.CinemaAdminService;
import com.woniu.util.MenuTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CinemaAdminServiceImpl implements CinemaAdminService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public List<Admin> selectCinemaAdmins(Integer pageIndex, Integer num) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("start", (pageIndex-1)*num);
        map.put("num", num);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Admin> admins = adminMapper.selectCinemaAdmins(map);
        for (Admin admin:admins) {
           admin.setOverDate(format.format(admin.getOverTime()));
        }
        return admins;
    }

    @Override
    public Integer count() {
        Integer count = adminMapper.count();
        return count;
    }

    @Override
    public Admin login(String username) {
        Admin admin = adminMapper.login(username);
        return admin;
    }

    @Override
    public Admin selectByPhone(String phone) {
        Admin adminByPhone = adminMapper.selectByPhone(phone);
        return adminByPhone;
    }

    @Override
    public void insert(Admin admin) {
        adminMapper.insertSelective(admin);
    }
}
