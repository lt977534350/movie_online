package com.woniu.orders.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.woniu.orders.constant.Constant;
import com.woniu.orders.entity.MenuOders;
import com.woniu.orders.entity.Order;
import com.woniu.orders.service.MenuOrdersService;
import com.woniu.orders.util.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: movie_online
 * @description:
 * @author: liutao
 * @create: 2019-09-26 14:28
 **/
@Controller
@Slf4j
public class MenuApi {
    @Resource
    private MenuOrdersService menuOrdersService;

    @RequestMapping("createMenu")
    @ResponseBody
    public String createMenu( Integer menuId,Integer aid) throws Exception {
        return menuOrdersService.insertMenuOrders(menuId, aid);
    }

    @RequestMapping("back")
    public String back(HttpServletRequest request, HttpServletResponse response) {
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
        MenuOders menuOders = menuOrdersService.selectMenuorders(outTradeNo);
        if (menuOders == null) {
            log.warn("收到未知的异步通知 outTradeNo:{}", outTradeNo);
            return "FAIL";
        }
        if (menuOders.getPaySuccessTime() != null) {
            log.debug("订单状态已完成 outTradeNo:{}", outTradeNo);
            return "SUCCESS";
        }
        int num = menuOrdersService.updateAdmimOvertimeAndMenuOrderSPaySuccessTime
                (outTradeNo, menuOders.getAid(), menuOders.getNum());
        return "success";


    }
}
