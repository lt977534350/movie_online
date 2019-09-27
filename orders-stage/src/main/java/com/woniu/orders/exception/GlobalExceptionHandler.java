package com.woniu.orders.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;

@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver{
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ex.printStackTrace();
		ModelAndView mav=new ModelAndView();
		if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){

			FastJsonJsonView view=new FastJsonJsonView();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("codeMessage", "error");
			map.put("message", ex.getMessage());
			view.setAttributesMap(map);
			mav.setView(view);

		}else {
			mav.setViewName("500");
			mav.addObject("message", "你的浏览器出现了故障,建议换一台电脑!");
		}
		return mav;
	}
}
