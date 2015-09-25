package com.sunwayland.core.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.sunwayland.core.exception.ValidateException;

/**
 * 纳入 spring 容器   aspect  通知才起作用;   JoinPoint  
 * @author Administrator
 * 
 * 在ace 容器中 不可代理接口类 ; 
 *
 */
//@Component    aop 处理 验证 效率 低, 改为 uitls 处理; 
//@Aspect()   
public class ControllerAop {
	  
	 
	private  Logger log = Logger.getLogger(ControllerAop.class);
	  
	/**通知：验证方法*/
	/**
	 * 任何通知方法可以将第一个参数定义为org.aspectj.lang.JoinPoint类型 
	 * 			（环绕通知需要定义第一个参数为ProceedingJoinPoint类型， 它是 JoinPoint 的一个子类）。
	 *      JoinPoint 接口提供了一系列有用的方法，
	 *      		比如 getArgs()（返回方法参数）、
	 *      		 getThis()（返回代理对象）、
	 *      		 getTarget()（返回目标）、 
	 *      		 getSignature()（返回正在被通知的方法相关信息）
	 *      		和 toString() （打印出正在被通知的方法的有用信息）。
	 * @throws Exception 
	 */
	  //===============================================================
	    
   // @Before(" execution(  * com..controller..*.my_create*(..)  ) " )
	public void before(JoinPoint joinPoint) throws Exception{
    	//com.mysql.jdbc.Driver
     	log.info(" ----------------Aspect-Before-----------------" + Thread.currentThread().getId());
    	
		Object [] args = joinPoint.getArgs();
		log.info(args.length);
		if( null  != args && args.length>0){
			for(Object o:args){
				 if( o instanceof  BindingResult  &&  ((BindingResult) o).hasErrors()   ){
//					 throw new BindingException("字段验证失败");
					 throw new ValidateException( ( BindingResult)o );
//					 throw new BindingException("")
				 }
			} 
		}
		
//		System.out.println("代理对象实例："+joinPoint.getThis().getClass());
//		System.out.println("目标对象实例："+joinPoint.getTarget().getClass());
//		System.out.println("目标对象方法的名称："+joinPoint.getSignature().getName());
//		System.out.println("表达式的信息："+joinPoint.toString());
		
		//如果想执行完通知的方法之后，阻止到目标对象，此时在通知的方法中抛出异常
		/*if(true){
			throw new RuntimeException();
		}*/
		
	}
    
    //===============================================================
     
    
//    @Around(value="action() ")
	public Object checkSecurity(  ProceedingJoinPoint joinPoint    ){
		/**joinPoint.proceed()方法放置到前面，相当于后置通知*/
		Object value = null;
		System.out.println( "--------------Around--------------------");
		try {
			value = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("验证方法是否合法，并符合操作！");
		
		Object [] args = joinPoint.getArgs();
		if(args!=null && args.length>0){
			for(Object o:args){
				System.out.println("参数："+o);
			}
		} 
		Signature signature = joinPoint.getSignature();
		 
		System.out.println(signature.getDeclaringTypeName());
		System.out.println(signature.getDeclaringType());
	   
		
		System.out.println("代理对象实例："+joinPoint.getThis().getClass());
		System.out.println("目标对象实例："+joinPoint.getTarget().getClass());
		System.out.println("目标对象方法的名称："+joinPoint.getSignature().getName());
		
		String name = joinPoint.getSignature().getDeclaringType().getName();
		 
		 
		System.out.println("表达式的信息："+joinPoint.toString());
		
//		/**joinPoint.proceed()方法放置到后面，相当于前置通知*/
//		Object value = null;
//		try {
//			value = joinPoint.proceed();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}

		System.out.println(" ----------------------------------");
		return value;
	}
    
    //===============================================================
    
//    @After(value="action()  ")
	public void checkSecurity(JoinPoint joinPoint){
		System.out.println("---------------after------------------");
		
		 
		
		System.out.println("验证方法是否合法，并符合操作！");
		Object [] args = joinPoint.getArgs();
		if(args!=null && args.length>0){
			for(Object o:args){
				System.out.println("参数："+o);
			}
		}
		System.out.println("代理对象实例："+joinPoint.getThis().getClass());
		System.out.println("目标对象实例："+joinPoint.getTarget().getClass());
		System.out.println("目标对象方法的名称："+joinPoint.getSignature().getName());
		System.out.println("表达式的信息："+joinPoint.toString());
		System.out.println("---------------------------------");
	}
	
	
	//=====================================================================
	
	//@AfterReturning(value="action() ",returning="returnValue")
	public void checkSecurity(JoinPoint joinPoint,Object returnValue){
		
		System.out.println("-----------------------AfterReturning-----------------------");
		System.out.println("验证方法是否合法，并符合操作！");
		Object [] args = joinPoint.getArgs();
		if(args!=null && args.length>0){
			for(Object o:args){
				System.out.println("参数："+o);
			}
		}
		System.out.println("代理对象实例："+joinPoint.getThis().getClass());
		System.out.println("目标对象实例："+joinPoint.getTarget().getClass());
		System.out.println("目标对象方法的名称："+joinPoint.getSignature().getName());
		System.out.println("表达式的信息："+joinPoint.toString());
		System.out.println("后置通知：在通知的方法中接收目标对象方法的返回值："+returnValue);
		
		System.out.println("----------------------------------------------");
		
	}
	
	//=================================================================
	
	//@AfterThrowing(value="action()  ",throwing="throwingValue")
	public void checkSecurity(JoinPoint joinPoint,Throwable throwingValue){
		
		System.out.println("------------------AfterThrowing----------------------");
		
		System.out.println("验证方法是否合法，并符合操作！");
		Object [] args = joinPoint.getArgs();
		if(args!=null && args.length>0){
			for(Object o:args){
				System.out.println("参数："+o);
			}
		}
		System.out.println("代理对象实例："+joinPoint.getThis().getClass());
		System.out.println("目标对象实例："+joinPoint.getTarget().getClass());
		System.out.println("目标对象方法的名称："+joinPoint.getSignature().getName());
		System.out.println("表达式的信息："+joinPoint.toString());
		System.out.println("异常通知：在通知的方法中捕获目标对象方法抛出的异常："+throwingValue);
		
		System.out.println("------------------------------------");
	}
	 
	
}
