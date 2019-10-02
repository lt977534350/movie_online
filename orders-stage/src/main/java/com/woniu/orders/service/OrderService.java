/**
 * FunName:  函数名
 * Description :   描述这个方法是干嘛的
 * Create Date:
 *
 * @exception Exception   异常没有处理
 * @param 参数名(如果没有删除改行)  参数说明
 * @return 返回值类型, 如没有, 删除该行 返回是否找到
 * @author LIUTAO
 */
package com.woniu.orders.service;

import com.woniu.orders.entity.Order;
import com.woniu.orders.entity.OrdersPO;
import com.woniu.orders.util.Count;
import com.woniu.orders.util.CountDetail;

import java.text.ParseException;
import java.util.List;

public interface OrderService {

    List<Order> selectOrder(int uid, Integer PageIndex) throws Exception;

    long selectCount(int uid) throws Exception;

    Order selectDatail(String oid) throws Exception;
    void updatebyOid(String oid, Byte ostate, int a_pid,String code)throws Exception;
    int updateStateByOid(String oid, Byte ostate)throws Exception;
    int deleteByOid(String Oid)throws Exception;

    String insertCreateOrders(Order order,Integer id [],Integer uid ,Integer msid)throws Exception;

    int updateOrderSuccess(Order order, int alipayNoticeLogId);

    List<CountDetail> selectOrdersSuccess()throws Exception;

    List<CountDetail> selectOneWeekOrdersSuccess(Integer aid)throws Exception;
    List<CountDetail> selectOneWeekOrdersturnover(Integer aid)throws Exception;


    List<CountDetail> selectOrdersFail() throws  Exception;

    List<Order> selectOrdersByAid(Integer aid,Integer pageIndex)throws  Exception;

    int selectCountByAid(Integer aid )throws Exception;

    int insertChangingTicket(String oldOrderId,Integer uid  )throws Exception;

    /**
     * 查询在此影院是否有改签
     * @param uid
     * @return
     * @throws Exception
     */
    Order selectChangeTicket(Integer uid )throws  Exception;


    int deleteOderIdIsNull(Integer uid)throws Exception;





}
  
