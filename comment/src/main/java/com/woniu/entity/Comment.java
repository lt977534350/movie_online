package com.woniu.entity;

import com.woniu.myutil.myeneity.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Comment {
    private Integer id;

    private String argue;

    private Byte userScore;

    private Integer mid;

    private Integer cid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String time;

    private Integer uid;
    private User user;
    private Integer likeNum;

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArgue() {
        return argue;
    }

    public void setArgue(String argue) {
        this.argue = argue == null ? null : argue.trim();
    }

    public Byte getUserScore() {
        return userScore;
    }

    public void setUserScore(Byte userScore) {
        this.userScore = userScore;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", argue='" + argue + '\'' +
                ", userScore=" + userScore +
                ", mid=" + mid +
                ", cid=" + cid +
                ", time=" + time +
                ", user=" + user +
                '}';
    }
}