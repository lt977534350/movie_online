package com.woniu.orders.service;

import com.woniu.myutil.myeneity.Menu;
import com.woniu.orders.entity.MenuOders;

public interface MenuOrdersService {
    String insertMenuOrders(Integer mid ,Integer aid)throws Exception;
    MenuOders selectMenuorders(String ordersNum);
    int updateAdmimOvertimeAndMenuOrderSPaySuccessTime(String odersNum,Integer aid,Integer period);



}
