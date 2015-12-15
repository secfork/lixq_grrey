package com.sunwayland.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.GenericParams;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.web.vo.Global;

@Controller
@RequestMapping("subscribe")
@ResponseBody 
@SuppressWarnings("unchecked")
public class SubscribeController extends GenericAction{
	
	@Autowired
	public  ThingLinxRest  rest ; 
	
	
	//(根据过滤条件查询订阅信息) 
	@RequestMapping( value="/select" , method =  RequestMethod.GET)
	public Object querySubscribe (
			@ModelAttribute(Global.session_key_user) User user ,
			@RequestParam Map  params 
			
			){
		 
		SuffixParams suffixParams = SuffixParams.get();
		
		suffixParams.getMap().putAll(params);
		
		// type 是必填项 ;
		suffixParams.getMap().put("type", "alarm");
		
		return rest.https.get(user, "/notify/subscribes", null, suffixParams);
		 
		 
	}
	
	
	
	@RequestMapping( value="/create" , method = RequestMethod.POST)
	public Object  createSub (
			@ModelAttribute(Global.session_key_user) User user,
			@RequestBody  String  body
			){
		
		 return rest.https.post(user, "/notify/subscribes", body, null	, null);
		 
	}
	
	
	
	
	// 根据id 获取订阅信息;
	
	
	//根据订阅id删除订阅)  
	@RequestMapping( value="/{subscribe_id}/delete" , method = RequestMethod.GET)
	public Object  delSubById (
			@ModelAttribute(Global.session_key_user) User user,
		    @PathVariable String subscribe_id 
			
			){
		
		GenericParams params = UrlParams.get().put("subscribe_id", subscribe_id);
		
		return rest.https.delete(user, "/notify/subscribes/{subscribe_id}", null,
				params , null );
		 
	}
	
	
 
	
 
	
	
}
