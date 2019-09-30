package com.woniu.orders.service.serviceIpml;

import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.woniu.myutil.myeneity.Admin;
import com.woniu.myutil.myeneity.Menu;
import com.woniu.orders.entity.MenuOders;
import com.woniu.orders.entity.MenuOdersExample;
import com.woniu.orders.mapper.auto.AdminMapper;
import com.woniu.orders.mapper.auto.MenuMapper;
import com.woniu.orders.mapper.auto.MenuOdersMapper;
import com.woniu.orders.service.MenuOrdersService;
import com.woniu.orders.util.AlipayConfig;
import com.woniu.orders.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: movie_online
 * @description:
 * @author: liutao
 * @create: 2019-09-26 14:35
 **/
@Service
public class MenuOrdersServiceImpl implements MenuOrdersService {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private MenuOdersMapper menuOdersMapper;
    @Resource
    private AdminMapper adminMapper;

    private DefaultAlipayClient alipayClient = new DefaultAlipayClient(
            AlipayConfig.GATEWAYURL, AlipayConfig.APP_ID, AlipayConfig.MERCHANT_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);

    @Override
    public String insertMenuOrders(Integer mid, Integer aid) throws Exception {


        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        /** 同步通知，支付完成后，支付成功页面*/
        alipayRequest.setReturnUrl("http://v266p12387.wicp.vip/web/feng/backstage/backstageIndex.html");
        /** 异步通知，支付完成后，需要进行的异步处理*/
        alipayRequest.setNotifyUrl("http://v266p12387.wicp.vip/orders/back");

        Menu menu = menuMapper.selectByPrimaryKey(mid);
        Date date = new Date();
        String orderNum = DateUtil.dateToFormatStr(date) + "a" + aid;
        //向数据库插入订单信息
        MenuOders menuOders = new MenuOders();
        menuOders.setAid(aid);
        menuOders.setMenuodersnum(orderNum);
        menuOders.setCTime(date);
        menuOders.setNum(menu.getPeriod());
        menuOders.setMenoy(menu.getMoney());
        menuOdersMapper.insertSelective(menuOders);
        //调用支付宝接口
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + orderNum + "\","
                + "\"total_amount\":\"" + menu.getMoney().toString() + "\","
                + "\"subject\":\"" + menu.getName() + "\","
                + "\"body\":\"1235\","
                + "\"timeout_express\":\"90m\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        /**转换格式*/
        String result = alipayClient.pageExecute(alipayRequest).getBody().replace('\"', '\'').replace('\n', ' ');
        return result;
    }

    @Override
    public MenuOders selectMenuorders(String ordersNum) {
        MenuOdersExample menuOdersExample = new MenuOdersExample();
        menuOdersExample.createCriteria().andMenuodersnumEqualTo(ordersNum);
        List<MenuOders> menuOders = menuOdersMapper.selectByExample(menuOdersExample);
        MenuOders menuOders1 = null;
        if (menuOders != null || menuOders.size() > 0) {
            menuOders1 = menuOders.get(0);
        }
        return menuOders1;
    }

    @Override
    public int updateAdmimOvertimeAndMenuOrderSPaySuccessTime(String odersNum, Integer aid, Integer period) {
        Admin oldadmin = adminMapper.selectByPrimaryKey(aid);
        Date date = new Date();
        MenuOders menuOders = new MenuOders();
        menuOders.setMenuodersnum(odersNum);
        menuOders.setPaySuccessTime(date);
        int i1 = menuOdersMapper.updateByPrimaryKeySelective(menuOders);
        //修改套餐到期时间
        Admin admin = new Admin();
        admin.setId(aid);
        Calendar calendar=null;
        if (oldadmin.getOvertime().getTime() < System.currentTimeMillis()) {
             calendar=Calendar.getInstance();
        } else {
            calendar=Calendar.getInstance();
            Date date1 =oldadmin.getOvertime();
            calendar.setTime(date1);
        }
        calendar.add(Calendar.YEAR, period);
        Date overtime = calendar.getTime();
        admin.setOvertime(overtime);
        int i = adminMapper.updateByPrimaryKeySelective(admin);
        return i;

    }


}
