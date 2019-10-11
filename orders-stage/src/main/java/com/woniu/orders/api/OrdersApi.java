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
package com.woniu.orders.api;

import com.woniu.myutil.myeneity.Admin;
import com.woniu.orders.constant.Constant;
import com.woniu.orders.entity.Order;
import com.woniu.orders.exception.TimeOverException;
import com.woniu.orders.service.OrderService;
import com.woniu.orders.util.Count;
import com.woniu.orders.util.CountDetail;
import com.woniu.orders.util.Page;
import com.woniu.orders.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.woniu.myutil.myeneity.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/profile/orders")
public class OrdersApi {
    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     * @param seatId 座位id数组
     * @param msid 排片id
     * @param session
     * @param uid 手机端传的用户id
     * @return
     * @throws Exception
     */
    @GetMapping("/confirm")
    @ResponseBody
    public Result createOrders(@RequestParam("id") Integer seatId[], Integer msid, HttpSession session ,Integer uid) throws Exception {

        User user = (User) session.getAttribute("user");
        if(user==null){
            if(uid==null){
                throw new Exception("请登录");
            }
        }else {
            uid=user.getId();
        }
        if (seatId.length <= 0 || msid == null) {
            return new Result("500", "选座失败", null, null);
        }

        String orderId = null;
        Order order = orderService.selectChangeTicket(uid);
        if (order != null) {
            orderId = orderService.insertCreateOrders(order, seatId, uid, msid);
            return new Result("200", "改签", "/web/profile/detail.html?oid=" + orderId, null);
        } else {
            orderId = orderService.insertCreateOrders(null, seatId, uid, msid);
        }

        return new Result("200", "选座成功", "/web/profile/detail.html?oid=" + orderId, null);
    }

    @GetMapping
    @ResponseBody
    public Result selectOrders(HttpSession session, Integer pageIndex,Integer uid) throws Exception {
        User user = (User) session.getAttribute("user");
        if(user==null){
            if(uid==null){
                //session和手机端传参都没有
                return  new Result("500", "请登录", null, null);
            }
        }else {
            //session存在，从session中取
            uid=user.getId();
        }
        if (pageIndex == null) {
            pageIndex = 1;
        }
        List<Order> orders = orderService.selectOrder(uid, pageIndex);
        int count = (int) orderService.selectCount(uid);
        Page page = new Page();
        page.setDataCount(count);
        page.setPageCount(count % Constant.Page.PAGE_DISPLAYED.getpageData() == 0 ? count / Constant.Page.PAGE_DISPLAYED.getpageData() : count / Constant.Page.PAGE_DISPLAYED.getpageData() + 1);
        page.setPageIndex(pageIndex);
        Result result = new Result("200", "success", page, orders);
        return result;
    }


    /**
     * 根据订单号查看详情
     *
     * @param oid 订单号
     * @return
     * @throws Exception
     */
    @RequestMapping("detail")
    @ResponseBody
    public Result detail(String oid) throws Exception {
        Order order = orderService.selectDetail(oid);
        return new Result("200", null, order, null);
    }

    /**
     * 用户删除订单，改变数据库字段，不物理删除，方便统计数据
     *
     * @param oid
     * @return
     * @throws Exception
     */
    @DeleteMapping
    @ResponseBody
    public Result del(String oid) throws Exception {
        if (oid == null) {
            return new Result("500", "删除失败", null, null);
        }
        int i = orderService.deleteByOid(oid);
        if (i == 1) {
            return new Result("200", "删除成功", null, null);
        }
        return new Result("500", "删除失败", null, null);
    }

    @GetMapping("count")
    @ResponseBody
    public Count selectOrderSuccessOrFail() throws Exception {
        List<CountDetail> success = orderService.selectOrdersSuccess();
        List<CountDetail> refund = orderService.selectOrdersFail();
        Count count = new Count();
        count.setFinish(success);
        count.setRefund(refund);
        System.out.println(count);
        return count;
    }

    @GetMapping("selectOrderWeek")
    @ResponseBody
    public Count selectOrdersSuccess(Integer aid) throws Exception {
        List<CountDetail> success = orderService.selectOneWeekOrdersSuccess(aid);
        List<CountDetail> turnover = orderService.selectOneWeekOrdersturnover(aid);
        Count count = new Count();
        count.setFinish(success);
        count.setTurnover(turnover);
        return count;
    }

    /**
     * 查询影院的订单情况
     *
     * @return
     */
    @RequestMapping("selectByAid")
    @ResponseBody
    public Result selectOrderSByAid(HttpSession session, Integer pageIndex) throws Exception {
       Admin admin = (Admin)session.getAttribute("cinemaAdmin");
        int aid = admin.getId();
        if (pageIndex == null) {
            pageIndex = 1;
        }
        List<Order> orders = orderService.selectOrdersByAid(admin.getId(), pageIndex);
        int dataCount = orderService.selectCountByAid(admin.getId());
        Page page = new Page();
        page.setPageIndex(pageIndex);
        page.setDataCount(dataCount);
        page.setPageCount(dataCount % 10 == 0 ? dataCount / 10 : dataCount / 10 + 1);
        return new Result("200", "success", page, orders);

    }

    /**
     * 插入一条信息，表示要改签改签接口
     *
     * @param orderId
     * @return
     */
    @PostMapping("insertchangingTicket")
    @ResponseBody
    public Result insertchangingTicket(String orderId, String cid,HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        Order order = orderService.selectDetail(orderId);
        if(order.getPalyTime().getTime()>System.currentTimeMillis()+30*60*1000){
            throw  new TimeOverException("改签时间已过");
        }
        //删除uid，且orderId为null的数据，或未支付的。一次只能改签一次,
        orderService.deleteOderIdIsNull(user.getId());
        int i = orderService.insertChangingTicket(orderId, user.getId());
        String url ="/web/feng/playpoint.html?cid="+cid;
        if (i!=0){
            return  new Result("200",url,null,null);
        }
        throw new Exception("改签失败");
    }


}

