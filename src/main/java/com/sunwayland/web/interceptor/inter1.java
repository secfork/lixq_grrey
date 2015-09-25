package com.sunwayland.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class inter1 extends HandlerInterceptorAdapter {

	 Log log = LogFactory.getLog(inter1.class);
	 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String id = request.getSession(true).getId();
		 log.info("来访:" +   request.getRequestURL());
		 log.info("session id = " + id);
		
		return super.preHandle(request, response, handler);
		
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		 
		String header = response.getHeader("Set-Cookie");
		log.info("Set-Cookie" + header);
		
		
	}

 

}
