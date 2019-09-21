package com.woniu.util;

import com.woniu.entity.Admin;
import lombok.Data;

import java.util.List;

@Data
public class MenuTime {
    //即将过期的影院管理列表
    private List<Admin> almostOverdue;
}
