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

import com.woniu.orders.constant.Constant;
import com.woniu.orders.entity.Order;
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
    //创建订单
    @GetMapping("/confirm/")
    @ResponseBody
    public Result createOrders ( Integer id[],Integer msid,HttpSession session) throws Exception {
    /*User user =(User)session.getAttribute("user");
     user.getId();*/
     if (id.length<=0||msid==null){
         return new Result("500","选座失败",null,null);
     }

        String orderId = null;
        try {
            orderId = orderService.insertCreateOrders(id, 1, 1);
            System.out.println(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result("500","选座失败",null,null);
        }
        System.out.println(1);
        return new Result("200","选座成功","/web/profile/detail.html?oid="+orderId,null);
    }

    @GetMapping
    @ResponseBody
    public Result selectOrders(Integer uid, Integer pageIndex) throws Exception {
        System.out.println(pageIndex);
        if (pageIndex ==null){
            pageIndex=1;
        }
        List<Order> orders = orderService.selectOrder(1, pageIndex);
        int count = (int)orderService.selectCount(1);
        Page page = new Page();
        page.setDataCount(count);
        page.setPageCount(count%Constant.Page.PAGE_DISPLAYED.getpageData()==0?count/Constant.Page.PAGE_DISPLAYED.getpageData():count/Constant.Page.PAGE_DISPLAYED.getpageData()+1);
        page.setPageIndex(pageIndex);
        Result result = new Result("200","success",page,orders);
        return result;
    }


    /**
     *
     * 根据订单号查看详情
     * @param oid 订单号
     * @return
     * @throws Exception
     */
    @RequestMapping("detail")
    @ResponseBody
    public Result  detail( String oid) throws Exception {
        Order order = orderService.selectDatail(oid);
        return new Result("200",null,order,null);
    }

    /**
     * 用户删除订单，改变数据库字段，不物理删除，方便统计数据
     * @param oid
     * @return
     * @throws Exception
     */
    @DeleteMapping
    @ResponseBody
    public Result  del(String oid) throws Exception {
        if (oid==null){
            return new Result("500","删除失败",null,null);
        }
        int i = orderService.deleteByOid(oid);
        if(i==1){
              return new Result("200","删除成功",null,null);
        }
       return new Result("500","删除失败",null,null);
    }
    @GetMapping ("count")
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
}
  
