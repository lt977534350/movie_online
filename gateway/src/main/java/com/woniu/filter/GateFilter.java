package com.woniu.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class GateFilter extends ZuulFilter {
    @Override
    //定义该过滤器的执行时机
    public String filterType() {
        return "pre";
    }

    @Override
    //定义该过滤器在多个过滤器之间的执行顺序（数字越小优先级越高）
    public int filterOrder() {
        return 0;
    }

    @Override
    //是否启用该过滤器
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String method = request.getMethod();
        if("post".equals(method)||"put".equals(method)||"delete".equals(method)){
            return true;
        }
        String uri = request.getRequestURI();
        ArrayList<String> list = new ArrayList<>();
        list.add("/api-user/user/login");
        list.add("/movie-stage/movie/bycid/");
        list.add("/cinema-stage/admin/login");
        list.add("/web/feng/backstage/login.html");
        list.add("/api-user/user/register");
        list.add("/api-user/user/register");
        list.add("/web/tao/mtype.html");
        list.add("/cinema-stage/cinema/all");
        list.add("/cinema-stage/cinema/bycid");
        list.add("/cinema-stage/cinema/byaid");
        list.add("/api-comment/comment");
        list.add("/api-comment/like");
        list.add("/movie-stage/movie/bymid");
        list.add("/movie-stage/movie/orderbytime/9");
        list.add("/movie-stage/movie/orderbyscore");
        list.add("/movie-stage/movie/orderbycid");
        list.add("/movie-stage/movie/orderbyname");
        list.add("/movie-stage/movie/movieon");
        list.add("/web/feng/index.html");
        list.add("/web/feng/hotmovies2.html");
        list.add("/web/feng/filmpage.html");
        list.add("/web/feng/playpoint.html");
        list.add("/web/feng/cinemaIndex.html");
        list.add("/web/tao/cinema-jump.html");
        list.add("/web/tao/movie-info.html");
        list.add("/orders/selectSeat");
        list.add("/orders/selectCinema");
        list.add("/orders/selectMovie");
        list.add("/movie-stage/movie/all");
        list.add("/movie-stage/movie/byid");
        list.add("/movie-stage/movie/movies");
        list.add("/movie-stage/movie/aftertoday");
        list.add("/orders/profile/orders/count");
        list.add("/orders/profile/orders");
        list.add("orders/pay/refund");
        list.add("/orders/pay/topay");
        list.add("/orders/pay/confirmChangingTicket");
        list.add("//api-user/type");
        list.add("/api-user/user/io");
        list.add("/api-user/user");
        list.add("/movie-stage/movie/example");
        list.add("/orders/pay/alipay_callback");
        list.add("/web/profile/orders.html");
        list.add("/cinema-stage/cinema");
        list.add("/api-user/user/upload");
        list.add("/api-user/type");
        list.add("/orders/selectmovie");
        list.add("/movie-stage/movie/byname");
        list.add("/orders/back");
        list.add("/cinema-stage/menu");
        list.add("/cinema-stage/admin/all");
        list.add("/cinema-stage/log");
        list.add("/cinema-stage/admin/getAdmin");
        list.add("/ordersgather.html");
        list.add("/orders/selectmovie");
        list.add("/orders/selectSeat");
        list.add("/orders/selectCinema");
        list.add("/web/profile/xseats.html");
        list.add("/orders/profile/orders/selectByAid");

        for (String myurl:list) {
            if (uri.contains(myurl)){
                System.out.println("ssss"+uri);
                return false;
            }
        }
        return true;
    }

    @Override
    //编写过滤逻辑
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        //获取request
        HttpServletRequest request = context.getRequest();
        //获取response
        HttpServletResponse response = context.getResponse();
        //获取session
        HttpSession session = request.getSession();
        //获取请求路径
        String url = request.getRequestURL().toString();
        //静态资源放行
        if (url.contains(".js")||url.contains(".css")||url.contains(".jpg")||url.contains(".png")
        ||url.contains(".otf")||url.contains(".eot")||url.contains(".svg")||url.contains(".ttf")||
                url.contains(".woff")||url.contains(".woff2")){
            context.setSendZuulResponse(true);
            return null;
        }
        System.out.println("路径---"+url);
        if(session.getAttribute("user")!=null&&session.getAttribute("admin")!=null&&session.getAttribute("cinemaAdmin")!=null){
            //都在登录状态直接放过
            return null;
        }else{
            if(url.contains("/profile/detail")||url.contains("/profile/orders")||url.contains("/profile/userinfo")||url.contains("/profile/userupdate")||url.contains("/profile/xseats")){
                if(session.getAttribute("user")==null){
                    //用户没登录，不允许访问
                    context.setSendZuulResponse(false);
                    try {
                        System.out.println("重定向了");
                        response.sendRedirect("/web/feng/index.html");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }else if(url.contains("/qian/check-list")||url.contains("/qian/cinema")||url.contains("/qian/cinema-detail")||url.contains("/qian/log")||url.contains("/qian/movie-manage")||url.contains("/qian/person")
                    ||url.contains("/qian/stage-index")){
                if(session.getAttribute("admin")==null){
                    //平台管理员未登录，不允许访问
                    context.setSendZuulResponse(false);
                    try {
                        response.sendRedirect("/web/feng/backstage/login.html");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }else{
                if(session.getAttribute("cinemaAdmin")==null){
                    //电影院管理员未登录，不允许访问
                    context.setSendZuulResponse(false);
                    try {
                        response.sendRedirect("/web/feng/backstage/login.html");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        }
    }
}
