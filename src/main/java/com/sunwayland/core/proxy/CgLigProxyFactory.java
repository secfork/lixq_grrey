package com.sunwayland.core.proxy;

import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.SessionAttributes;

import com.sunwayland.web.vo.Global;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
 
 
public class CgLigProxyFactory   implements MethodInterceptor {
    
	
	  private Object target ;
	 
	  public Object getInstance(Object target) {   
	        this.target = target;  
	        Enhancer enhancer = new Enhancer();  
	        enhancer.setSuperclass(this.target.getClass());  
	        // 回调方法  
	        
	        enhancer.setCallback(this);  
	        // 创建代理对象  
	        return enhancer.create();  
	 }

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		
		    System.out.println("事物开始");  
		    System.out.println( method.getName());
	        Object object = proxy.invokeSuper(obj, args);  
	        System.out.println("事物结束");  
	        return object ;
	} 
	 
	 
	public CgLigProxyFactory(){
		super();
	}
	
	@SuppressWarnings("unchecked")
	public  static <T> T getProxy (T target){
		
		  CgLigProxyFactory  fac =   new CgLigProxyFactory();
		      T instance = (T) fac.getInstance(target);
		  return instance ;
	}
 
  
}
