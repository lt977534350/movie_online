package com.woniu.orders.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.woniu.orders.constant.Constant;
import com.woniu.orders.entity.*;
import com.woniu.orders.mapper.auto.VipPoMapper;
import com.woniu.orders.mapper.custom.TasklistMapper;
import com.woniu.orders.mapper.custom.UserVipMapper;
import com.woniu.orders.service.*;
import com.woniu.orders.util.AlipayConfig;
import com.woniu.orders.util.DateUtil;
import com.woniu.orders.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @program: tickets-online
 * @description:
 * @author: liutao
 * @create: 2019-09-17 16:14
 **/
@Controller
@RequestMapping("pay")
@Slf4j
public class PayApi {
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserVipMapper userVipMapper;
    @Autowired
    private VipService vipService;
    @Autowired
    private UserVipService userVipService;
    @Autowired
    private TasklistMapper tasklistMapper;
    @Autowired
    private SeatInfoService seatInfoService;


    @PostMapping("topay")
    @ResponseBody
    public String toPay(String oid) throws Exception {
        //跟据订单查询订单金额等
        System.out.println(oid);
        Order order = orderService.selectDatail(oid);
        //订单号
        String outTradeNo = oid;
        //订单金额
        String totalAmount = order.getMoney().toString();
        //商品名
        String subject = order.getMovieName() + "-订单号：" + oid;
        String pay = alipayService.webPagePay(outTradeNo, totalAmount, subject);
        System.out.println(pay);
        return pay;
    }


    //这是异步通知
    @RequestMapping("alipay_callback")
    @ResponseBody
    public Object personal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("收到异步通知");
        Map<String, String[]> aliParams = request.getParameterMap();
        //用以存放转化后的参数集合
        Map<String, String> conversionParams = new HashMap<String, String>();
        for (Iterator<String> iter = aliParams.keySet().iterator(); iter.hasNext(); ) {
            String key = iter.next();
            String[] values = aliParams.get(key);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "uft-8");
            conversionParams.put(key, valueStr);
        }
        log.debug("收到异步通知 参数：{}", conversionParams);
        conversionParams.remove("sign_type");
        try {
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(
                    conversionParams,
                    AlipayConfig.ALIPAY_PUBLIC_KEY,
                    "utf-8",
                    AlipayConfig.SIGN_TYPE
            );

            if (!alipayRSACheckedV2) {
                log.debug("签名验签失败", conversionParams);
                return "FAIL";
            }
        } catch (AlipayApiException e) {
            log.error("签名验证失败", e);
        }

        //支付宝分配给开发者的应用Id
        String appId = conversionParams.get("app_id");
        if (!AlipayConfig.APP_ID.equals(appId)) {
            log.warn("收到未知的异步通知 appId:{}", appId);
            return "FAIL";
        }
        //获取商户之前传给支付宝的订单号（商户系统的唯一订单号）
        String outTradeNo = conversionParams.get("out_trade_no");
        //获取商户之前传给支付宝的订单号（商户系统的唯一订单号）
        Order order = orderService.selectDatail(outTradeNo);
        if (order == null) {
            log.warn("收到未知的异步通知 outTradeNo:{}", outTradeNo);
            return "FAIL";
        }

        if (order.getOstate() == Constant.OrderStatusEnum.ORDER_SUCCESS.getCode()) {
            log.debug("订单状态已完成 outTradeNo:{}", outTradeNo);
            return "SUCCESS";
        }

        String tradeStatus = conversionParams.get("trade_status");

        if (!"TRADE_SUCCESS".equals(tradeStatus)) {
            log.debug("收到异步回调通知 outTraderNo:{}, orderStatus:{}", outTradeNo, tradeStatus);
            return "FAIL";
        }

        String notifyTime = conversionParams.get("notify_time");
        String gmtCreate = conversionParams.get("gmt_create");
        String gmtPayment = conversionParams.get("gmt_payment");
        String tradeNo = conversionParams.get("trade_no");
        String outBizNo = conversionParams.get("out_biz_no");
        String buyerLogonId = conversionParams.get("buyer_id");
        String sellerId = conversionParams.get("seller_id");
        String totalAmount = conversionParams.get("total_amount");
        String receiptAmount = conversionParams.get("receipt_amount");
        String invoiceAmount = conversionParams.get("invoice_amount");
        String buyerPayAmount = conversionParams.get("buyer_pay_amount");

        Alipayinfo alipayinfo = new Alipayinfo();
        alipayinfo.setNotifyTime(DateUtil.strToDate(notifyTime));
        alipayinfo.setGmtCreate(DateUtil.strToDate(gmtCreate));
        alipayinfo.setGmtPayment(DateUtil.strToDate(gmtPayment));
        alipayinfo.setTradeno(tradeNo);
        alipayinfo.setOutTradeNo(outTradeNo);
        alipayinfo.setOutBizno(outBizNo);
        alipayinfo.setBuyerLogonId(buyerLogonId);
        alipayinfo.setSellerId(sellerId);
        alipayinfo.setTotalAmount(Double.parseDouble(totalAmount));
        alipayinfo.setReceiptAmount(Double.parseDouble(receiptAmount));
        alipayinfo.setInvoiceAmount(Double.parseDouble(invoiceAmount));
        alipayinfo.setBuyerPayAmount(Double.parseDouble(buyerPayAmount));
        alipayinfo.setTradeStatus((byte) 20);

        int row = this.alipayService.insertAliPayNoticeLog(alipayinfo);
        if (row <= 0) {
            log.error("记录日志失败 alipayinfo: {}", alipayinfo);
            return "FAIL";
        }

        row = orderService.updateOrderSuccess(order, alipayinfo.getId());
        if (row <= 0) {
            log.error("更新订单状态失败 order_no: {}", order.getOrderId());
            return "FAIL";
        }
        //执行业务操作
        //查询VIP信息
        List<VipPo> vipPos = vipService.selectVipByAid(order.getAid());
        //查询消费金额
        List<UserVipPO> userVipPOList = userVipService.
                select(order.getUid(), order.getAid());
        UserVip userVip = new UserVip();
        //
        int vipId = userVipService.getVipId(vipPos,
                userVipPOList.get(0).getConsume() + order.getMoney());
        System.out.println("vip" + vipId);
        userVip.setUid(order.getUid());
        userVip.setAid(order.getAid());
        userVip.setVid(vipId);
        userVip.setConsume(alipayinfo.getTotalAmount());
        row = this.userVipMapper.updateUserVip(userVip);
        if (row <= 0) {
            log.warn("更新用户vip消费信息失败 uid: {} aid: {} consume: {}",
                    order.getOrderId(),
                    order.getAid(),
                    userVip.getConsume()
            );
        }
        return "SUCCESS";
    }

    //退款
    @RequestMapping("refund")
    @ResponseBody
    public Result refund(String oid, String refundReason) throws Exception {
        System.out.println(oid);
        System.out.println(refundReason);
        Order order = orderService.selectDatail(oid);
        String msg = alipayService.insertRefund(oid, refundReason, order.getMoney().toString(), "HZ01RF001");
        if ("退款成功".equals(msg)) {
            int i = orderService.updateStateByOid(oid, new Byte("40"));
            //返回座位信息
            String seat = order.getSeatId();
            String[] split = seat.split("-");
            List<Seatinfo> seatinfoList = new ArrayList<>();
            for (int j = 0; j < split.length; j++) {
                Seatinfo seatinfo = new Seatinfo();
                seatinfo.setId(Integer.parseInt(split[j]));
                seatinfoList.add(seatinfo);
                //还原座位表信息
            }
            int num = seatInfoService.updateStateToN(seatinfoList);
        }
        return new Result("200", msg, null, null);
    }


}
