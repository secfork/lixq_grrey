package com.sunwayland.core.vo;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class webResult {
	private Object err ;
	private Object ret ;
	private Object msg ;
	
	
	private  Integer  total ;
	private  Object  data ;

	private  Object  order ;
	
	
	
	
	
	
	public Object getOrder() {
		return order;
	}
	public webResult setOrder(Object order) {
		this.order = order;
		return this ;
	}
	
	public Integer getTotal() {
		return total;
	}
	public webResult setTotal(Integer total) {
		this.total = total;
		return this ;
	}
	public Object getData() {
		return data;
	}
	public webResult setData(Object data) {
		this.data = data;
		return this ;
	}
	public Object getMsg() {
		return msg;
	}
	public webResult setMsg(Object msg) {
		this.msg = msg;
		return this ;
	}
	public Object getErr() {
		return err;
	}
	public webResult setErr(Object err) {
		this.err = err;
		return this ;
	}
	public Object getRet() {
		return ret;
	}
	public webResult setRet(Object ret) {
		this.ret = ret;
		return this ;
	}
	
	
	public static webResult get(){
		return new webResult();
	}
	
	
}
