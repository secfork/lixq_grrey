package com.sunwayland.web.exceptionhandler;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.sunwayland.core.JsOrder.JsOrder;
import com.sunwayland.core.exception.CheckException;
import com.sunwayland.core.exception.ExceptionMessage;
import com.sunwayland.core.exception.ValidateException;
import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.vo.FieldErrorMsg;
import com.sunwayland.web.vo.ErrCode;



@ControllerAdvice( {"com.sunwayland.web.controller"})
@ResponseBody
public class ExceptionHandl extends GenericAction {
 
	 Logger  log = Logger.getLogger(this.getClass()); 
	
	  private  void printStackTrace( Throwable e){
		  e.printStackTrace();
	  }

 

	  //----------------------------未知错误!--------------------------------------------- 
	 // @ExceptionHandler( value=Exception.class )
	  public Object  e1( Throwable  e  ){ 
		 log.error("xxxxxxxxxxe1:" + e.getClass() ); 
//		 IllegalStateException
		 printStackTrace(e);
		 return   RESP_ERR( e.getClass().getName()) ;   
	  }
	  
	  @ExceptionHandler( value=UnknownHostException.class )
	  public Object  exxx( Throwable  e ){ 
		 log.error("xxxxxxxxxxe1:" + e.getClass() ); 
		 
		 printStackTrace(e);
		 return   RESP_ERR( e.getClass().getName()) ;   
	  }
	   
	  
	  
	  //-------------------------------------------------------------------------
//	  @ExceptionHandler(value ={ResourceAccessException.class
//			                  , HttpClientErrorException.class
//	  })
	  @ExceptionHandler(value ={RestClientException.class })
	  public Object e2( Throwable e){
		 log.error("e2"); 
		 log.error(e);
		 printStackTrace(e);
		 return  RESP_ERR( ErrCode.rest_err);
	  }

	  //-------------------------------------------------------------------------
	  /**
	   * session 失效 重新登录; 
	   * @param e
	   * @return
	   */
	  @ExceptionHandler({HttpSessionRequiredException.class  })
	  public Object e3(Throwable e ){
		  log.error("e3");
		 return   RESP_ORDER(JsOrder.login);
	  }
	  
 
	  //-------------------------------------------------------------------------

 
	  
	  //-----------------------------字段验证 --------------------------------------------
	  @ExceptionHandler( value=ValidateException.class)
	  public Object  e6 ( Throwable e ){
		  log.error("e6");
		  ValidateException ve  = (ValidateException) e ;
		  List<BindingResult> results = ve.getValidateResults();
		  
		  HashMap<String, List> res = new HashMap<String, List>();
		  
		  for( BindingResult er : results){
			   List<ObjectError> allErrors = er.getAllErrors();
			   String name = er.getObjectName();
			   ArrayList  valis = new ArrayList();
			   			   
			   for( ObjectError es : allErrors ){
					  FieldError  fe = (FieldError) es ;  
					  valis.add(new FieldErrorMsg(fe.getField(), fe.getDefaultMessage()));
			   }
			   
			   res.put(name, valis); 
		  } 
		 
		  return RESP_ERR(res);
	  }
	  
	  //------------------------------- id 合法性 check  -----------------------------------------
	  @ExceptionHandler( value=CheckException.class)
	  public Object  e7( Throwable e){
		  String message = e.getMessage();
		  return  RESP_ERR(message);  
	  }
	  
	  //---------------------------------  sql 语句, sql 数据错误 ----------------------------------------------
	  @ExceptionHandler( value= SQLException.class)
	  public  Object e8(Throwable e){
		  return RESP_ERR("SQLException");
	  }

	  @ExceptionHandler( value= ExceptionMessage.class)
	  public  Object e9(Throwable e   ){
		  return RESP_ERR( e.getMessage());
	  }

	  // 参数解析失败; 
	  @ExceptionHandler( value= IllegalStateException.class)
	  public  Object e10(Throwable e   ){
		  log.error("参数解析失败");
		  e.printStackTrace();
		  return RESP_ERR( e.getMessage());
	  }
 
	  
	  //  requestbody 格式不正确!; JsonMappingException
	  // org.springframework.http.converter.HttpMessageNotReadableException
	  @ExceptionHandler( value= HttpMessageNotReadableException.class)
	  public  Object e11(Throwable e   ){
		  
		  HttpMessageNotReadableException ee = (HttpMessageNotReadableException) e ;
		  String message = ee.getMessage();
		  
		  Throwable cause = ee.getCause();
		  
		  if( cause instanceof JsonMappingException){
			  JsonMappingException je = (JsonMappingException) cause;
			  List<Reference> path = je.getPath();
			  
			  String f ="" ; 
			  for( Reference r : path){  
				 f += r.getFieldName() +" "; 
			  } 
			  return RESP_ERR("不合法字段:"+f);
			  
//			  return RESP_ERR(path);
			  
			  
		  }
		  
		  log.error("requestbody 参数解析失败");
		  e.printStackTrace();
		  return RESP_ERR( e.getMessage());
	  }
	  
	  
	   
	  
// requset err 	  
// org.springframework.web.bind.ServletRequestBindingException 

	  // rest err; 
	 //org.springframework.web.client.ResourceAccessException 
	  //java.net.ConnectException
	  
	 // author 
//	  org.apache.shiro.UnavailableSecurityManagerException
	  
	  
	  
}
