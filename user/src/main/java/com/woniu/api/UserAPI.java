package com.woniu.api;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.woniu.entity.UserType;
import com.woniu.myutil.myeneity.User;
import com.woniu.service.TypeService;
import com.woniu.service.UserService;
import com.woniu.util.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
@RestController
@RequestMapping("user")
public class UserAPI {
    @Resource
    private UserService userService;
    @Resource
    private TypeService typeService;
    private static String filename=null;

    /**
     * 查询用户信息，同时判断用户是否已经登陆；
     * @param
     * @return
     * @throws Exception
     */
    @GetMapping
    public Result selectUser(HttpSession session)throws Exception{
        /*获取user对象*/
        User user = (User) session.getAttribute("user");
        System.out.println("当前登录用户："+user);
        String code="success";
        String message="";
        /*判断是否为空，为空则未登录*/
        if(user==null){//未登录
            code="error";
            message="请先登陆";
            return new Result(code,message,null,null);
        }else{
            return new Result(code,message,userService.getUser(user.getId()),null);
        }
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     * @throws Exception
     */
    @PutMapping
    public Result updateUser(User user,int[] type)throws Exception{
        /*修改user信息*/
        userService.updateUser(user);
        if(type!=null){
            /*删除用户电影类型信息*/
            typeService.deleteUserType(user.getId());
            /*新增userType信息*/
            for(int i=0;i<type.length;i++){
                UserType userType=new UserType();
                userType.setUid(user.getId());
                userType.setTid(type[i]);
                typeService.insertUserType(userType);
            }
        }
        return new Result("success",null,null,null);
    }

    /**
     * 新增用户信息
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping
    public Result insertUser(User user)throws Exception{
        userService.insertUser(user);
        return new Result("success",null,null,null);
    }

    /**
     * 删除用户信息
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{uid}")
    public Result deleteUser(@PathVariable Integer uid)throws Exception{
        userService.deleteUser(uid);
        return new Result("success",null,null,null);
    }

    /**
     * 上传图片
     * @param file
     * @param request
     * @return
     * @throws IOException
     */

    @PostMapping("upload")
    public Result upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String message="ok";
        /*获取session并取出user用户*/
        HttpSession session = request.getSession();
        User user1 = (User) session.getAttribute("user");
        /*判断文件是否为空*/
        if(!file.isEmpty()){
            long l = System.currentTimeMillis();
            filename = String.valueOf(l)+file.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf(".")+1);
            /*获取当前路径；*/
            /*String filepath = TouristDynamicController.class.getResource("/").getPath()+"static/upload";*/
            String filepath=null;
            /*判断是否符合格式*/
            if(suffix.equals("mp4")){
                filepath = System.getProperty("user.dir")+"\\"+"src\\main\\resources\\static\\upload";
            }else if (suffix.equals("jpg")||suffix.equals("png")||suffix.equals("gif")){
                filepath=request.getServletContext().getRealPath("/upload/images");
            }
            /*设置文件存储目录，没有就先生成*/
            String url=filepath+"\\"+filename;
            if(suffix.equals("jpg")||suffix.equals("gif")||suffix.equals("png")||suffix.equals("mp4")){
                File upload=new File(filepath);
                if(!upload.exists()){
                    upload.mkdirs();
                }
                File url1=new File(url);
                try{
                    file.transferTo(url1);
                    /*查询用户图片信息*/
                    User user = userService.getUser(user1.getId());
                    String phone = user.getUpic().substring(user.getUpic().lastIndexOf("=") + 1);
                    /*删除该用户之前的图片*/
                    File file1 = new File(phone);
                    if(file1.exists()){
                        file1.delete();
                    }
                    /*替换数据库upic信息*/
                    user.setUpic("/api-user/user/io?img="+url.replace("\\","/"));
                    userService.updateUser(user);
                }catch (Exception e){
                    e.printStackTrace();
                    message="服务器异常";
                }
            }else{
                message="请选择图片";
            }
        }
        return new Result("success",message,null,null);
    }
    //将图片使用io流写出到页面上；
    @GetMapping("io")
    public void selectPicture(String img, HttpServletResponse response)throws Exception{
        FileInputStream fi=new FileInputStream(new File(img));
        OutputStream os = response.getOutputStream();
        byte[] by=new byte[1024];
        int len=0;
        while((len=fi.read(by))>0){
            os.write(by,0,len);
        }
        fi.close();
        os.close();
    }
    /**
     * 用户登录验证
     */
    @RequestMapping("login")
    public Result login(HttpServletResponse resp, HttpServletRequest req, HttpSession session, String username, String password, String phonenum, String code, Boolean rem) throws Exception {
        //判断用户是否已登录，防止重复登录
        Cookie[] cookies = req.getCookies();
        if (cookies!=null&&cookies.length>0){
            for (Cookie cookie:cookies) {
                if ("loguser".equals(cookie.getName())&&cookie.getValue()!=null){
                    return new Result("error","已在别处登录！",null,null);
                }
            }
        }
        //如果是用户登录
        if(username!=null&&password!=null&&!"".equals(username)&&!"".equals(password)){
            if((phonenum==null||phonenum=="")&&(code==null||"".equals(code))){
                //判断user传入的信息是否真实有效
                User userByName = userService.selectByName(username);
                if(userByName==null){//没有该用户名
                    return new Result("ufail","用户名错误！",null,null);
                }
                if(!userByName.getPassword().equals(password)){//密码不正确
                    return new Result("pwdfail","密码错误！",null,null);
                }
                //用户名密码均正确，将登录信息存入session
                session.setAttribute("user",userByName);
                System.out.println("存入的session"+session.getId());
                String string = JSONObject.toJSONString(userByName);
                String mystring = URLEncoder.encode(string,"utf-8");
                Cookie cookie = new Cookie("loguser",mystring);
                //判断是否记住我
                System.out.println("remember:"+rem);
                if(rem!=null){
                    cookie.setMaxAge(7*24*60*60);
                }
                cookie.setPath("/");
                resp.addCookie(cookie);
                return new Result("success","登录验证成功！",userByName,null);
            }
        }else if((phonenum!=null||!"".equals(phonenum))&&(code!=null||"".equals(code))){//手机验证码登录
            if((username==null||"".equals(username))&&(password==null||"".equals(password))){
                String num = (String) session.getAttribute("user_phonenum");
                String mobile_code = session.getAttribute("user_mobile_code").toString();
                User user = userService.selectByPhone(phonenum);
                System.out.println("user---"+user);
                if(user==null){
                    return new Result("pfail","该电话号码不存在！",null,null);
                }
                if(num.equals(phonenum)&&mobile_code.equals(code)){
                    String string = JSONObject.toJSONString(user);
                    String mystring = URLEncoder.encode(string,"utf-8");
                    Cookie cookie = new Cookie("loguser",mystring);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                    session.setAttribute("user",user);
                    session.removeAttribute("user_mobile_code");
                    session.removeAttribute("user_phonenum");
                    return new Result("success","登录成功！",user,null);
                }else{
                    return new Result("cfail","验证码不正确！",null,null);
                }
            }
        }
        return new Result("badmsg","验证信息格式有误！",null,null);

    }
    //用户注册
    @RequestMapping("register")
    public Result register(HttpSession session, String username, String password, String telphone, String code) throws Exception {
        //注册信息验证
        String phonenum = (String) session.getAttribute("user_phonenum");
        String mobile_code = session.getAttribute("user_mobile_code").toString();
        if(!phonenum.equals(telphone)||!code.equals(mobile_code)){
            return new Result("fail","验证码错误!",null,null);
        }
        User user = userService.selectByName(username);
        if(user!=null){
            return new Result("fail","用户名已存在!",null,null);
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setPhone(telphone);
        userService.insertUser(newUser);
        session.removeAttribute("user_phonenum");
        session.removeAttribute("user_mobile_code");
        return new Result("success","注册成功！",null,null);
    }

    /**
     * 发送验证码
     */
    @RequestMapping("sendCode")
    public Result getCode(String phone, HttpSession session) throws Exception {
        System.out.println("请求进来了---"+phone);
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
                session.setAttribute("user_mobile_code", mobile_code);
                session.setAttribute("user_phonenum", phone);
            }
            method.releaseConnection();

            return new Result("success", submitResult, null, null);
        } else {
            return new Result("false", "手机号不能为空", null, null);
        }
    }

    /**
     *用户注销，将其从session删除
     */
    @DeleteMapping("userlogout")
    public Result userLogout(HttpSession session, HttpServletResponse resp){
        System.out.println("---"+session.getAttribute("user"));
        User user = (User) session.getAttribute("user");
        if(user==null){
            System.out.println("请求进入");
            return new Result("fail","非法操作！",null,null);
        }
        Cookie cookie = new Cookie("loguser", null);
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        session.removeAttribute("user");
        return new Result("success","注销成功",null,null);
    }

}
