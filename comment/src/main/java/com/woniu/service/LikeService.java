package com.woniu.service;

import com.woniu.entity.Like;

import java.util.List;

public interface LikeService {
    /*根据评论id查询总点赞数*/
    int getCount(Integer cid)throws Exception;
    /*新增点赞信息*/
    void insertList(Like like)throws Exception;
    /*删除点赞信息*/
    void deleteLike(Integer uid,Integer cid)throws Exception;
    /*查询点赞信息*/
    List<Like> getLikes(Integer uid)throws Exception;
}
