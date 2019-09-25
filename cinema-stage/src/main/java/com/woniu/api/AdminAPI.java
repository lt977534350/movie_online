package com.woniu.api;


import com.woniu.service.CinemaAdminService;
import com.woniu.util.Page;
import com.woniu.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.woniu.myutil.myeneity.Admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

@RestController
@RequestMapping("admin")
public class AdminAPI {
    @Resource
    private CinemaAdminService cinemaAdminService;
    //获取电影院管理员的所有信息
    @GetMapping("all")
    public Result selectCinemaAdmins(Integer pageIndex) throws Exception{
        if(pageIndex==null||pageIndex==0){
            pageIndex = 1;
        }
        Integer num = 5;
        List<Admin> admins = cinemaAdminService.selectCinemaAdmins(pageIndex, num);
        Integer count = cinemaAdminService.count();
        Page page = new Page(pageIndex, count%num==0?count/num:count/num+1, count);
        return new Result("success",null,page,admins);
    }
    @GetMapping("login")
    public Result login(HttpSession session, String username, String password, String phone_num, String check_code) throws Exception{
        //管理员以账号密码登录
        if(username!=null&&password!=null&&!"".equals(username)&&!"".equals(password)){
            if((phone_num==null||phone_num=="")&&(check_code==null||"".equals(check_code))){
                //判断user传入的信息是否真实有效
                Admin admin = cinemaAdminService.login(username);
                if(admin==null){//没有该用户名
                    return new Result("fail","用户名错误！",null,null);
                }
                if(!admin.getPassword().equals(password)){//密码不正确
                    return new Result("fail","密码错误！",null,null);
                }
                //用户名密码均正确，将登录信息存入session
                if((int)admin.getLevel()==2){
                    session.setAttribute("cinemaAdmin",admin);
                }
                session.setAttribute("admin",admin);
                return new Result("success","登录验证成功！",admin,null);
            }
        }else if((phone_num!=null||!"".equals(phone_num))&&(check_code!=null||!"".equals(check_code))){//手机验证码登录
            if((username==null||"".equals(username))&&(password==null||"".equals(password))){
                String num = (String) session.getAttribute("admin_phonenum");
                String mobile_code = session.getAttribute("admin_mobile_code").toString();
                Admin adminByPhone = cinemaAdminService.selectByPhone(phone_num);
                if(adminByPhone==null){
                    return new Result("fail","该电话号码不存在！",null,null);
                }

                if(num.equals(adminByPhone.getPhone())&&mobile_code.equals(check_code)){
                    session.setAttribute("admin",adminByPhone);
                    session.removeAttribute("admin_mobile_code");
                    session.removeAttribute("admin_phonenum");
                    return new Result("success","登录成功！",adminByPhone,null);
                }else{
                    return new Result("fail","验证码不正确！",null,null);
                }
            }
        }
        return new Result("error","验证信息格式有误！",null,null);
    }
    //用户注册
    @RequestMapping("register")
    public Result register(HttpSession session, String username, String password, String telphone, String code, String name) throws Exception {
        //注册信息验证
        String phonenum = (String) session.getAttribute("admin_phonenum");
        String mobile_code = session.getAttribute("admin_mobile_code").toString();
        if(!phonenum.equals(telphone)||!code.equals(mobile_code)){
            return new Result("fail","验证码错误!",null,null);
        }
        Admin admin = cinemaAdminService.login(username);
        Admin adminByPhone = cinemaAdminService.selectByPhone(phonenum);
        if(admin != null){
            return new Result("fail","用户名已存在!",null,null);
        }
        if(adminByPhone != null){
            return new Result("fail","该手机已被注册!",null,null);
        }
        Admin newadmin = new Admin();
        newadmin.setUsername(username);
        newadmin.setPassword(password);
        newadmin.setPhone(telphone);
        newadmin.setLevel(Byte.parseByte("2"));
        if(name!=null&&!"".equals(name)){
            newadmin.setName(name);
        }
        cinemaAdminService.insert(newadmin);
        session.removeAttribute("user_phonenum");
        session.removeAttribute("user_mobile_code");
        return new Result("success","注册成功！",null,null);
    }
    /**
     * 发送验证码
     */
    @RequestMapping("sendCode")
    public Result getCode(String phone, HttpSession session) throws Exception {
        System.out.println("请求进来了---" + phone);
        if (phone != null && !"".equals(phone)) {
            String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
            HttpClient client = new HttpClient();
            PostMethod method = new PostMethod(Url);

            client.getParams().setContentCharset("utf-8");
            method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=utf-8");

            int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);

            String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

            //提交短信
            NameValuePair[] data = {
                    //查看用户名是登录用户中心->验证码短信->产品总览->APIID
                    new NameValuePair("account", "C30864193"),
                    //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
                    new NameValuePair("password", " 0e40508e89cc3dc06f2f23c6166f45ba "),
                    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                    new NameValuePair("mobile", phone),
                    new NameValuePair("content", content),
            };
            method.setRequestBody(data);
            //短信提交的返回结果
            String submitResult = "";
            client.executeMethod(method);

            submitResult = method.getResponseBodyAsString();

            Document doc = DocumentHelper.parseText(submitResult);
            Element root = doc.getRootElement();

            String cod = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");
            /**
             * cod代表调用接口收到的返回值,返回值中的code码如果为2,则是成功的意思:
             * <code>2</code>
             * <msg>查询成功</msg>
             * <num>856</num>
             */
            if ("2".equals(cod)) {
                //将验证码和对应的手机号存入session
                session.setAttribute("admin_mobile_code", mobile_code);
                session.setAttribute("admin_phonenum", phone);
            }
            method.releaseConnection();

            return new Result("success", submitResult, null, null);
        } else {
            return new Result("false", "手机号不能为空", null, null);
        }
    }

    @PutMapping("update")
    public Result updateAdmin(HttpSession session, Integer aid, String username, String password, String phone) throws Exception{
        System.out.println("-----"+username);
        Admin admin = (Admin) session.getAttribute("admin");
        //没有参数传入
        if((username==null||"".equals(username))&&(password==null||"".equals(password))&&(phone==null||"".equals(phone))){
            return new Result("fail","无更新内容，请检查操作",null,null);
        }
        //管理员处于登录状态
        if(admin!=null){
            Admin updateAdmin = new Admin();
            updateAdmin.setId(aid);
            if(username!=null&&!"".equals(username)){
                updateAdmin.setUsername(username);
            }
            if(password!=null&&!"".equals(password)){
                updateAdmin.setPassword(password);
            }
            if(phone!=null&&!"".equals(phone)){
                updateAdmin.setPhone(phone);
            }
            cinemaAdminService.update(admin);
            session.removeAttribute("admin");
            return new Result("success","更新成功！",null,null);
        }else{
            return new Result("nopermission","非法操作！未登录！",null,null);
        }
    }
    /**
     * 从session中获取admin
     */
    @GetMapping("getAdmin")
    public Result getAdminFromSession(HttpSession session) throws Exception{
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin==null){
            return new Result("fail","未登录！",null,null);
        }
        System.out.println("请求进入=---"+admin);
        return new Result("success",null,admin,null);
    }
    /**
     * 影院管理员账户更改
     */
    @PutMapping("updatecinema")
    public Result updateCinemaAdmin(HttpSession session, Integer aid, String username, String newpassword, String oldpassword, String phone) throws Exception{
        String cinemaAdmin = (String) session.getAttribute("cinemaAdmin");
        //没有参数传入
        if((username==null||"".equals(username))&&(newpassword==null||"".equals(newpassword))&&(phone==null||"".equals(phone))){
            return new Result("fail","无更新内容，请检查操作",null,null);
        }
        //管理员处于登录状态
        if(cinemaAdmin!=null){
            Admin adminByAid = cinemaAdminService.selectByAid(aid);
            if(!adminByAid.getPassword().equals(oldpassword)){
                return new Result("fail","旧密码输入不正确",null,null);
            }
            Admin updateAdmin = new Admin();
            updateAdmin.setId(aid);
            if(username!=null&&!"".equals(username)){
                updateAdmin.setUsername(username);
            }
            if(newpassword!=null&&!"".equals(newpassword)){
                updateAdmin.setPassword(newpassword);
            }
            if(phone!=null&&!"".equals(phone)){
                updateAdmin.setPhone(phone);
            }
            cinemaAdminService.update(updateAdmin);
            session.removeAttribute("cinemaAdmin");
            return new Result("success","更新成功！",null,null);
        }else{
            return new Result("nopermission","非法操作！未登录！",null,null);
        }
    }
}
