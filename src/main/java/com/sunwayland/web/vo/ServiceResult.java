package com.sunwayland.web.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
 
 @JsonInclude( JsonInclude.Include.NON_NULL) 
public class ServiceResult    {
	
    private Object  ret ; 
	private Object  err ;
	private Object  msg ; 
	
	public Object getRet() {
		return ret;
	}
	public void setRet(Object ret) {
		this.ret = ret;
	}
	public Object getErr() {
		return err;
	}
	public void setErr(Object err) {
		this.err = err;
	}
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	} 
	
	
	 
	
	
	 
 
}
