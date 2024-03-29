package com.woniu.serviceimpl;

import com.woniu.entity.CinemaPhoto;
import com.woniu.entity.Photo;
import com.woniu.mapper.CinemaPhotoMapper;
import com.woniu.mapper.PhotoMapper;
import com.woniu.myutil.myeneity.Vip;
import com.woniu.mapper.VipMapper;
import com.woniu.myutil.myeneity.Admin;
import com.woniu.mapper.AdminMapper;
import com.woniu.service.CinemaAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CinemaAdminServiceImpl implements CinemaAdminService {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private VipMapper vipMapper;
    @Resource
    private PhotoMapper photoMapper;
    @Resource
    private CinemaPhotoMapper cinemaPhotoMapper;

    @Override
    public List<Admin> selectCinemaAdmins(Integer pageIndex, Integer num) throws Exception {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("start", (pageIndex-1)*num);
        map.put("num", num);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Admin> admins = adminMapper.selectCinemaAdmins(map);
        for (Admin admin:admins) {
            if(admin.getOverTime()!=null){
                admin.setOverDate(format.format(admin.getOverTime()));
            }
        }
        return admins;
    }

    @Override
    public Integer count() throws Exception {
        Integer count = adminMapper.count();
        return count;
    }

    @Override
    public Admin login(String username) throws Exception {
        Admin admin = adminMapper.login(username);
        return admin;
    }

    @Override
    public Admin selectByPhone(String phone) throws Exception {
        Admin adminByPhone = adminMapper.selectByPhone(phone);
        return adminByPhone;
    }

    @Override
    public void insert(Admin admin) throws Exception {
        adminMapper.insertSelective(admin);
    }

    @Override
    public void update(Admin admin) throws Exception {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public Admin selectByAid(Integer aid) throws Exception {
        Admin adminByAid = adminMapper.selectByPrimaryKey(aid);
        return adminByAid;
    }

    @Override
    public List<Vip> selectVipByAid(Integer aid) throws Exception {
        List<Vip> vips = vipMapper.selectVipByAid(aid);
        return vips;
    }

    @Override
    public void updateVip(Vip vip) throws Exception {
        vipMapper.updateByPrimaryKeySelective(vip);
    }

    @Override
    public void insertVipByAid(Vip vip) {
        vipMapper.insertSelective(vip);
    }

    @Override
    public Admin selectAdminByShortName(String shortName) {
        Admin admin = adminMapper.selectAdminByShortName(shortName);
        return admin;
    }

    @Override
    public void updateLogo(Integer id, String logo) {
        //先删掉以前的图片
        //根据id查询
        Admin admin = adminMapper.selectByPrimaryKey(id);
        if(admin.getLogo() != null){

            String oldLogo = "c:"+admin.getLogo().replaceFirst("/cinema-stage","");
            //如果oldLogo是绝对路径，则删除
            File file = new File(oldLogo);

            System.out.println(file.getName());

            if(file.exists()){
                file.delete();
            }
        }

        //再添加新的图片
        adminMapper.updateLogo(id,logo);

    }

    @Override
    public void insertPic(Integer id, String picName) {
        Photo photo = new Photo(null, picName, "影院图片");
        photoMapper.insert(photo);
        cinemaPhotoMapper.insert(new CinemaPhoto(null,id,photo.getId()));
    }

}
