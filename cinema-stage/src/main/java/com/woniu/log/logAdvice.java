package com.woniu.log;


import com.woniu.entity.Log;
import com.woniu.mapper.LogMapper;
import com.woniu.myutil.myeneity.Admin;
import com.woniu.service.LogService;
import com.woniu.util.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.LogManager;

/**
 * @program: movie_online
 * @description:
 * @author: liutao
 * @create: 2019-09-27 09:10
 **/
@Component("log")
public class logAdvice {
    @Resource
    private LogService logService;
    public Object insertlog(ProceedingJoinPoint pjp) throws Throwable {
        Result result = null;
        try {
            //得到方法执行所需的参数
            Object[] args = pjp.getArgs();
            //明确调用业务层方法（切入点方法）
            result = (Result) pjp.proceed(args);
            Object sig = pjp.getSignature();
            MethodSignature msig = null;
            msig = (MethodSignature) sig;
            /*Object target = pjp.getTarget();*/
            Method currentMethod = msig.getMethod();
            /*Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());*/
            Log log = new Log();
            if ("login".equals(currentMethod.getName())) {
                if ("success".equals(result.getCode())) {
                    log.setUsername((String) args[1]);
                    log.setOperation("登录");
                    log.setOptime(new Date());
                    log.setOptype("1");
                    logService.insertLog(log);
                }
            }
            if ("register".equals(currentMethod.getName())) {
                if ("success".equals(result.getCode())) {
                    log.setUsername((String) args[1]);
                    log.setOperation("注册");
                    log.setOptime(new Date());
                    log.setOptype("1");
                    logService.insertLog(log);
                }
            }
              if ("logout".equals(currentMethod.getName())) {
                if("success".equals(result.getCode())){
                    if(result.getObject()==null){
                        return result;
                    }
                    log.setUsername((String) result.getObject());
                    log.setOperation("注销");
                    log.setOptime(new Date());
                    log.setOptype("1");
                    logService.insertLog(log);
                }
            }
              if ("updateCinemaAdmin".equals(currentMethod.getName())) {
                if("success".equals(result.getCode())){
                    log.setUsername((String) result.getObject());
                    log.setOperation("更新密码");
                    log.setOptime(new Date());
                    log.setOptype("1");
                    logService.insertLog(log);
                }
            }
               if ("insertMenu".equals(currentMethod.getName())) {
                if("success".equals(result.getCode())){
                    HttpSession session=  (HttpSession) args[3];
                    Admin admin = (Admin)session.getAttribute("admin");
                    log.setUsername(admin.getUsername());
                    log.setOperation("新增套餐");
                    log.setOptime(new Date());
                    log.setOptype("1");
                    logService.insertLog(log);
                }
            }
                if ("deleteMenuById".equals(currentMethod.getName())) {
                if("success".equals(result.getCode())){
                    HttpSession session=  (HttpSession) args[1];
                    Admin admin = (Admin)session.getAttribute("admin");
                    log.setUsername(admin.getUsername());
                    log.setOperation("删除套餐");
                    log.setOptime(new Date());
                    log.setOptype("3");
                    logService.insertLog(log);
                }
            }
                if ("update".equals(currentMethod.getName())) {
                if("success".equals(result.getCode())) {
                    HttpSession session = (HttpSession) args[4];
                    Admin admin = (Admin) session.getAttribute("admin");
                    log.setUsername(admin.getUsername());
                    log.setOperation("修改套餐");
                    log.setOptime(new Date());
                    log.setOptype("2");
                    logService.insertLog(log);
                }
                }



        } catch (Exception e) {
           e.printStackTrace();
            throw e;
        }
        return result;
    }
}
