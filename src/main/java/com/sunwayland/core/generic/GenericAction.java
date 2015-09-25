package com.sunwayland.core.generic;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sunwayland.core.JsOrder.JsOrder;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.web.vo.ErrCode;

@SuppressWarnings({ "rawtypes", "unchecked" })  
@SessionAttributes("user")
public class GenericAction    {
     
	@Autowired
	public  ThingLinxRest  rest ; 
	
	
	public  static  Object  RESP ( Object  result ){
		  HashMap map  = new HashMap (); 
		  map.put("ret",result ) ; 
		  return map ; 
	}
	
	
	public  static  Object  RESP ( String key ,  Object  result ){
		HashMap map  = new HashMap (); 
		map.put( key,result ) ; 
		return map ; 
	}
	
	
	
	public static Object  RESP_MSG ( Object object ){
		  HashMap map  = new HashMap (); 
		  map.put("msg",object) ; 
		  return map ; 
	}
	
	public  static  Object  RESP ( Object...  result ){
		  HashMap map  = new HashMap (); 
		  map.put("ret",result ) ; 
		  return map ; 
	}
	
 
	 
	public  static  Object  RESP_ERR ( Object  result ){
		HashMap map  = new HashMap (); 
		map.put("err",result ) ; 
		return map ; 
	}
	
	
	
	
	
	public static Object RESP_PAGE (int total , List list){
		HashMap map  = new HashMap (); 
		map.put("total",total ) ; 
		map.put("data",list ) ; 
		return map ; 
	}
	
	 public static  Object  RESP_ORDER (JsOrder  order){
		  HashMap map  = new HashMap (); 
		  map.put("order", order) ;
		  return map ;
	 }
	  
	 
	 
     
 
	 
	 
	 
	
}
