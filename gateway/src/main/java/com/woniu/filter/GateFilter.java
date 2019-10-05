package com.woniu.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
        StringBuffer url = request.getRequestURL();
        List<String> list = new ArrayList<>();
        list.add("localhost:9001/api-user/user/login");
        list.add("localhost:9001/web/feng/backstage/login.html");
        list.add("localhost:9001/api-user/user/register");
        list.add("localhost:9001/web/tao/mtype.html");
        list.add("localhost:9001/cinema-stage/cinema/all");
        list.add("localhost:9001/cinema-stage/cinema/bycid");
        list.add("localhost:9001/api-comment/comment");
        list.add("localhost:9001/api-comment/like");
        list.add("localhost:9001/movie-stage/movie/bymid");
        list.add("localhost:9001/movie-stage/movie/orderbytime");
        list.add("localhost:9001/movie-stage/movie/orderbyscore");
        list.add("localhost:9001/movie-stage/movie/orderbycid");
        list.add("localhost:9001/movie-stage/movie/orderbyname");
        list.add("localhost:9001/movie-stage/movie/movieon");
        list.add("localhost:9001/web/index.html");
        list.add("localhost:9001/web/feng/cinemaIndex.html");
        for (String myurl:list) {
            if (myurl.equals(url)){
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
        //获取session
        HttpSession session = request.getSession();

        System.out.println("gateway里面的session"+session.getId());
        //登录验证信息可以存入session中
        //可以通过下述代码响应错误信息,程序不调用API接口
//       if(session.getAttribute("user")==null){
//            context.setSendZuulResponse(false);
//            context.setResponseBody("{\"code\":\"500\",\"message\":\"no login\"}");
//        }
        return null;
    }
}
