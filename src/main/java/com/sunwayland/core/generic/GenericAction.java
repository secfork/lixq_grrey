package com.sunwayland.core.generic;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sunwayland.core.JsOrder.JsOrder;
import com.sunwayland.core.vo.webResult;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.web.vo.ErrCode;

@SuppressWarnings({ "rawtypes", "unchecked" })  
@SessionAttributes("user")
public class GenericAction    {
     

	
	public  static  Object  RESP ( Object  result ){
		  
		  return   webResult.get().setRet(result);
		 
	}
	
	
	public  static  Object  RESP ( String key ,  Object  result ){
		HashMap map  = new HashMap (); 
		map.put( key,result ) ; 
		return map ; 
	}
	
	
	
	public static Object  RESP_MSG ( Object object ){ 
		  
		  return  webResult.get().setMsg(object); 
	}
	
	public  static  Object  RESP ( Object...  result ){ 
		
		  return webResult.get().setRet(result);
		  
	}
	
 
	 
	public  static  Object  RESP_ERR ( Object  result ){ 
		return  webResult.get().setErr(result);
	}
	
	
	
	
	
	public static Object RESP_PAGE (int total , List list){ 
		
		return  webResult.get().setTotal(total).setData(list);
	}
	
	 public static  Object  RESP_ORDER (JsOrder  order){ 
		 
		  return   webResult.get().setOrder(order);
	 }
	  
	 
	 
     
 
	 
	 
	 
	
}
