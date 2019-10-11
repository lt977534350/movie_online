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


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;

import com.alipay.api.request.*;

import com.woniu.orders.constant.Constant;
import com.woniu.orders.entity.*;

import com.woniu.orders.mapper.auto.AlipayinfoMapper;
import com.woniu.orders.service.AlipayService;
import com.woniu.orders.service.OrderService;
import com.woniu.orders.util.AlipayConfig;


import com.woniu.orders.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AlipayServiceImpl implements AlipayService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AlipayinfoMapper alipayinfoMapper;



    /**
     * 调取支付宝接口 web端支付
     */
    DefaultAlipayClient alipayClient = new DefaultAlipayClient(
            AlipayConfig.GATEWAYURL, AlipayConfig.APP_ID, AlipayConfig.MERCHANT_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);

    /**
     * 调取支付宝接口app端支付
     */
    AlipayClient alipayClients = new DefaultAlipayClient(
            AlipayConfig.GATEWAYURL, AlipayConfig.APP_ID, AlipayConfig.MERCHANT_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);


    @Override
    public String webPagePay(String outTradeNo, String totalAmount, String subject) throws Exception {

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        /** 同步通知，支付完成后，支付成功页面*/
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        /** 异步通知，支付完成后，需要进行的异步处理*/
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);


        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"total_amount\":\"" + totalAmount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"1235\","
                + "\"timeout_express\":\"90m\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        /**转换格式*/
        String result = alipayClient.pageExecute(alipayRequest).getBody().replace('\"', '\'').replace('\n', ' ');

        return result;
    }


    @Override
    public String insertRefund(String outTradeNo, String refundReason, String refundAmount, String outRequestNo) throws Exception {
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        /** 调取接口*/
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"refund_amount\":\"" + refundAmount + "\","
                + "\"refund_reason\":\"" + refundReason + "\","
                + "\"out_request_no\":\"" + outRequestNo + "\"}");
        String result = alipayClient.execute(alipayRequest).getBody();
        //对返回数据进行解析
        Map map = (Map) JSONObject.parse(result);
        Map<String, String> map1 = (Map<String, String>) map.get("alipay_trade_refund_response");
        map1.put("sign", map.get("sign").toString());
        System.out.println(map1);

        int i = 0;
        int out_trade_no = 0;
        if ("Success".equals(map1.get("msg"))) {
            Alipayinfo alipayinfo = new Alipayinfo();
            alipayinfo.setOutTradeNo(map1.get("out_trade_no"));
            alipayinfo.setTradeStatus((byte) Constant.OrderStatusEnum.ORDER_CLOSE.getCode());
            alipayinfo.setRefundFee(Double.parseDouble(map1.get("refund_fee")));
            alipayinfo.setGmtRefund(DateUtil.strToDate(map1.get("gmt_refund_pay")));
            alipayinfo.setRedundmsg(refundReason);
            i = alipayinfoMapper.updateByPrimaryKeySelective(alipayinfo);
        }
        if (i == 1 ) {
            return "退款成功";
        } else {
            System.out.println(11111111);
            return "退款失败";
        }

    }

    @Override
    public String query(String outTradeNo) throws AlipayApiException {
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        /**请求接口*/
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\"," + "\"trade_no\":\"" + "" + "\"}");
        /**转换格式*/
        String result = alipayClient.execute(alipayRequest).getBody();
        return result;
    }

    @Override
    public String close(String outTradeNo) throws AlipayApiException {
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\"," + "\"trade_no\":\"" + "" + "\"}");
        String result = alipayClient.execute(alipayRequest).getBody();
        return result;
    }

    @Override
    public String refundQuery(String outTradeNo, String outRequestNo) throws Exception {
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
        /** 请求接口*/
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"out_request_no\":\"" + outRequestNo + "\"}");

        /** 格式转换*/
        String result = alipayClient.execute(alipayRequest).getBody();

        return result;
    }


    @Override
    public int insertAliPayNoticeLog(Alipayinfo alipayinfo) {
        int returnResult = alipayinfoMapper.insertSelective(alipayinfo);
        return returnResult;
    }






    @Override
    public String appPagePay(String outTradeNo, String totalAmount, String subject) throws Exception {
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();

        /** 同步通知，支付完成后，支付成功页面*/
        alipayRequest.setReturnUrl("orderInfo.html");
        /** 异步通知，支付完成后，需要进行的异步处理*/
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        /**销售产品码（固定）*/
        String productCode = "QUICK_WAP_WAY";

        /** 进行赋值 */
        AlipayTradeWapPayModel wapPayModel = new AlipayTradeWapPayModel();
        wapPayModel.setOutTradeNo(outTradeNo);
        wapPayModel.setSubject(subject);
        wapPayModel.setTotalAmount(totalAmount.toString());
        wapPayModel.setBody("商品名称");
        wapPayModel.setTimeoutExpress("2m");
        wapPayModel.setProductCode(productCode);
        alipayRequest.setBizModel(wapPayModel);

        /** 格式转换*/
        String result = alipayClients.pageExecute(alipayRequest).getBody().replace('\"', '\'').replace('\n', ' ');
        return result;
    }


}


