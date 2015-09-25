package com.sunwayland.core.aop;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
 
   
@Component
@Aspect   
public class MethodLogger {
   
  private  Log log = LogFactory.getLog( getClass());
	  
  @Around("execution( * com.sunwayland.web.service..*.*(..))" ) 
  public Object around(ProceedingJoinPoint point) throws Throwable {
	  
	   // 调用服务之前; 
    
	  Object result = point.proceed(); 
	  //调用服务后; 
	  MethodSignature cast = MethodSignature.class.cast(point.getSignature());
	  Signature signature = point.getSignature();
	 
	  
	  String name = MethodSignature.class.cast(point.getSignature()).getMethod().getName();
	  
	  Object[] args = point.getArgs();
	  
	  
	  log.info(" 拦截  @loggable || service包   记录日志!!" + name+"("+ Arrays.toString(args)+")" );
    
	  
      /*  
         long start = System.currentTimeMillis();
          Logger.info(
	      "#%s(%s): %s in %[msec]s",
	      MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
	      point.getArgs(),
	      result,
	      System.currentTimeMillis() - start
	    );*/
    return result;
  }
  
  
   
  
  
  
}