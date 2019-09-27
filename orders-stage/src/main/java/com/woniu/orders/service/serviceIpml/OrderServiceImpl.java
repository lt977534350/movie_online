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
package com.woniu.orders.service.serviceIpml;

import com.woniu.orders.constant.Constant;
import com.woniu.orders.entity.*;
import com.woniu.orders.mapper.auto.*;
import com.woniu.orders.mapper.custom.OrderMapper;
import com.woniu.orders.mapper.custom.UserVipMapper;
import com.woniu.orders.service.OrderService;
import com.woniu.orders.service.SeatInfoService;
import com.woniu.orders.service.VipService;
import com.woniu.orders.util.Count;
import com.woniu.orders.util.CountDetail;
import com.woniu.orders.util.DateUtil;
import com.woniu.orders.util.Seat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrdersPOMapper ordersPOMapper;
    @Resource
    private OrderMapper orderMapper;
    @Autowired
    private MovieShowtimeMapper movieShowtimeMapper;
    @Autowired
    private TasklistPOMapper tasklistPOMapper;
    @Autowired
    private SeatInfoService seatInfoService;
    @Autowired
    private UserVipMapper userVipMapper;
    @Autowired
    private UserVipPOMapper userVipPOMapper;
    @Autowired
    private VipService vipService;


    @Override
    public List<Order> selectOrder(int uid, Integer PageIndex) throws ParseException {
        //封装查询数据
        Map<String, Integer> map = new HashMap<>();
        map.put("uid", uid);
        int num = 5;
        map.put("start", (PageIndex - 1) * num);
        map.put("num", num);

        List<Order> orders = orderMapper.selectOrder(map);
        //由于座位是存的字符串，以，隔开，在这里拆1分放入list集合
        for (Order order : orders) {
            //格式化日期显示到前台，根据当前日期动态变化
            order.setFormatTime(DateUtil.formatDate(order.getPalyTime()) + "\n"
                    + DateUtil.dateToString(order.getPalyTime()));
            String seat = order.getSeat();
            String[] split = seat.split("=");
            List<Seat> list = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                Seat seatname = new Seat();
                seatname.setSeatName(split[i]);
                list.add(seatname);

            }
            order.setSeatList(list);

            //将数据库中的code转为字符串信息
            order = this.ostateToString(order);
        }
        return orders;
    }

    //根据传入的时间与当前时间比较，该显示什么，今天，明天，后天，其他则显示周几


    @Override
    public long selectCount(int uid) {
        OrdersPOExample example = new OrdersPOExample();
        example.createCriteria().andUidEqualTo(uid)
                .andOstateBetween(
                        Integer.valueOf(1).byteValue(),
                        Integer.valueOf(49).byteValue())
                .andIsdelEqualTo(Integer.valueOf(0).byteValue());
        long count = this.ordersPOMapper.countByExample(example);
        return count;
    }


    @Override
    public Order selectDatail(String oid) throws ParseException {

        Order order = orderMapper.selectDetail(oid);
        order.setFormatTime(DateUtil.
                formatDate(order.getPalyTime()) + "\n" + DateUtil.dateToString(order.getPalyTime()));
        List<Seat> list = new ArrayList<>();
        String seat = order.getSeat();
        String[] split = seat.split("=");
        for (int i = 0; i < split.length; i++) {
            Seat seatname = new Seat();
            seatname.setSeatName(split[i]);
            list.add(seatname);
        }
        order.setSeatList(list);
        order = this.ostateToString(order);

        return order;
    }


    @Override
    public List<CountDetail> selectOrdersFail() throws Exception {
       return orderMapper.selectOrderRefund();
    }

    @Override
    public List<CountDetail>  selectOrdersSuccess() throws Exception {
         return orderMapper.selectOrderSuccess();


    }

    @Override
    public List<CountDetail> selectOneWeekOrdersSuccess(Integer aid) throws Exception {
         return orderMapper.selectOneWeekOrderSuccess(aid);
    }

    @Override
    public List<CountDetail> selectOneWeekOrdersturnover(Integer aid) throws Exception {
         return orderMapper.selectOneWeekOrderTurnover(aid);
    }

    @Override
    public int updateOrderSuccess(Order order, int alipayNoticeLogId) {
        OrdersPOExample example = new OrdersPOExample();
        example.createCriteria()
                .andOrderIdEqualTo(order.getOrderId())
                .andOstateEqualTo(order.getOstate());

        OrdersPO po = new OrdersPO();
        po.setPayinfoId(alipayNoticeLogId);
        po.setCode(String.format("http://api.k780.com:88/?app=qr.get&data=%s&level=L&size=6", order.getOrderId()));
        po.setOstate(Byte.valueOf(String.valueOf(Constant.OrderStatusEnum.PAID.getCode())));
        int row = this.ordersPOMapper.updateByExampleSelective(po, example);
        return row;
    }

    @Override
    public int deleteByOid(String Oid) throws Exception {
        //如果未支付，还原座位信息
        Order order = orderMapper.selectDetail(Oid);


        int num = 0;
        if (order.getOstate() == Constant.OrderStatusEnum.NO_PAY.getCode()) {
            String seatId = order.getSeatId();
            String[] split = seatId.split("-");
            List<Seatinfo> seatinfoList = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                Seatinfo seatinfo = new Seatinfo();
                seatinfo.setId(Integer.parseInt(split[i]));
                seatinfoList.add(seatinfo);
            }
             seatInfoService.updateStateToN(seatinfoList);
        }
        num =orderMapper.updateIsDel(Oid);





        return num;
    }

    @Override
    public String insertCreateOrders(Integer[] seatId, Integer uid, Integer msid) throws Exception {
        //查询还有这些票吗,循环拆分数组
        List<Seatinfo> seatinfoList = new ArrayList<>();
        String s = "";
        for (Integer integer : seatId) {
            Seatinfo seatinfo = new Seatinfo();
            seatinfo.setId(integer);
            s = s + integer + "-";
            seatinfoList.add(seatinfo);
        }
        s = s.substring(0, s.length() - 1);
        List<Seatinfo> seatinfoList1 = seatInfoService.selectStateByList(seatinfoList, msid);
        System.out.println(seatinfoList1);
        for (Seatinfo seatinfo : seatinfoList1) {
            System.out.println(seatinfo);
            if ("LK".equals(seatinfo.getState())) {
                //有票被卖出
                return "票被卖出";
            }
        }

        int updateNum = seatInfoService.updateState(seatinfoList);
        System.out.println("更新的数" + updateNum);
        System.out.println(seatId.length);
        if (updateNum != seatId.length) {
            throw new Exception();
        }
        //查询影院id
        int aid = movieShowtimeMapper.selectAid(msid);

        //查询在此影院的消费情况
        UserVip userVip = userVipMapper.selectConsume(uid, aid);
        //查询此影院的对应消费额度的vip信息
        List<VipPo> vipPos = vipService.selectVipByAid(aid);
        Double discount =1d;
        if (userVip == null) {
            UserVipPO userVipPO = new UserVipPO();
            userVipPO.setAid(aid);
            userVipPO.setConsume(0d);
            userVipPO.setUid(uid);
            userVipPO.setVid(vipPos.get(0).getId());
            userVipPOMapper.insertSelective(userVipPO);
        }else {
            //获取折扣信息
            for (VipPo vipPo : vipPos) {
                if (vipPo.getId().equals(userVip.getVid())){
                    discount=vipPo.getVdiscount();
                    break;
                }
            }

        }



        //根据放映表id，查询票价
        Double aDouble = movieShowtimeMapper.selectPrice(msid);
        //创建订单
        OrdersPO order = new OrdersPO();
        order.setSeatId(s);
        order.setMoney(aDouble * seatId.length*discount);

        //获取当前时间
        Date date = new Date();
        //创建订单号
        order.setOrderId(DateUtil.dateToFormatStr(date) + uid);
        order.setCTime(date);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < seatinfoList1.size(); i++) {
            if (i < seatinfoList1.size() - 1) {
                stringBuffer.append(seatinfoList1.get(i).getRow() + "排" + seatinfoList1.get(i).getColumn() + "座" + "=");
            } else {
                stringBuffer.append(seatinfoList1.get(i).getRow() + "排" + seatinfoList1.get(i).getColumn() + "座");
            }


        }
        order.setSeat(stringBuffer.toString());
        order.setUid(uid);
        order.setOstate((byte) Constant.OrderStatusEnum.NO_PAY.getCode());
        order.setMovieShowtimeId(msid);
        int i = ordersPOMapper.insertSelective(order);
        int index = 0;
        if (i != 0) {
            Tasklist tasklist = new Tasklist();
            tasklist.setTaskdataid(order.getOrderId());
            tasklist.setTasktime(new Date(date.getTime() + 1 * 60 * 1000));
            tasklist.setTaskname("订单超时");
            index = tasklistPOMapper.insertSelective(tasklist);
        }
        return order.getOrderId();

    }

    //一个工具将订单状态转为字符串信息
    public Order ostateToString(Order order) {
        if (order.getOstate() == Constant.OrderStatusEnum.NO_PAY.getCode()) {
            //未付款
            order.setOstateMsg(Constant.OrderStatusEnum.NO_PAY.getValue());
            //给前端传剩余付款时间数
            int time = (int) ((order.getcTime().getTime() + 1 * 60 * 1000 - System.currentTimeMillis()) / 1000);
            order.setLeftPaySecond(time);
        } else if (order.getOstate() == Constant.OrderStatusEnum.PAID.getCode()) {
            //已付款
            //电影开场前30分钟支持退款，改签
            if ((order.getPalyTime().getTime() - System.currentTimeMillis()) > 30 * 1000) {
                order.setOstateMsg(Constant.OrderStatusEnum.PAID.getValue());
            } else {
                order.setOstateMsg("订单完成");
            }
        } else if (order.getOstate() == Constant.OrderStatusEnum.ORDER_CLOSE.getCode()) {
            order.setOstateMsg(Constant.OrderStatusEnum.ORDER_CLOSE.getValue());
        } else if (order.getOstate() == Constant.OrderStatusEnum.ORDER_SUCCESS.getCode()) {
            order.setOstateMsg(Constant.OrderStatusEnum.ORDER_SUCCESS.getValue());
        } else if (order.getOstate() == Constant.OrderStatusEnum.CANCELED.getCode()) {
            order.setOstateMsg(Constant.OrderStatusEnum.CANCELED.getValue());
        }
        return order;
    }

    @Override
    public void updatebyOid(String oid, Byte ostate, int a_pid, String code) throws Exception {

    }

    @Override
    public int updateStateByOid(String oid, Byte ostate) throws Exception {
        OrdersPO ordersPO = new OrdersPO();
        ordersPO.setOstate(ostate);
        ordersPO.setOrderId(oid);
        int i = ordersPOMapper.updateByPrimaryKeySelective(ordersPO);
        return i ;

    }
}