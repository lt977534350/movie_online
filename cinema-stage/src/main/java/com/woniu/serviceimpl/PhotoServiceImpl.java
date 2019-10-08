package com.woniu.serviceimpl;

import com.woniu.entity.Photo;
import com.woniu.mapper.CinemaPhotoMapper;
import com.woniu.mapper.PhotoMapper;
import com.woniu.service.PhotoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Resource
    private PhotoMapper photoMapper;
    @Resource
    private CinemaPhotoMapper cinemaPhotoMapper;

    @Override
    public List<Photo> selectPicByAid(Integer aid) throws Exception{
        List<Photo> photoList = photoMapper.selectPicByAid(aid);

        return photoList;
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        //删除本地图片
        Photo photo = photoMapper.selectByPrimaryKey(id);
        if(photo.getUrl() != null){
            File file = new File("c:" + photo.getUrl().replaceFirst("/cinema-stage", ""));
            if(file.exists()){
                file.delete();
            }

        }

        //删除photo表里的数据
        photoMapper.deleteByPrimaryKey(id);

        //删除cinemaphoto表里的数据
        cinemaPhotoMapper.deleteByPid(id);


    }
}
