package com.sunwayland.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.TicketUrl;
import com.sunwayland.web.vo.Global;

@Controller
@ResponseBody
@RequestMapping("ticket")
public class TicketController  extends GenericAction{
	
	@RequestMapping( value="/{system_id}",  method = RequestMethod.POST)
	public Object  bindTicket ( @ModelAttribute( Global.session_key_user) User user ,
								@PathVariable  String system_id,
								@RequestBody String body ){
		
		// body = { sn:xxx, privilege:['p1', ...]}
		
		return rest.https.post(user, TicketUrl.bind, body, UrlParams.get().system_id(system_id), SuffixParams.get());		
		 
	}
	
	@RequestMapping( value="/{system_id}",  method = RequestMethod.DELETE )
	public Object  unBindTicket ( @ModelAttribute( Global.session_key_user) User user ,
								  @PathVariable  String system_id){
		 
		return rest.https.post(user, TicketUrl.unbind, null , UrlParams.get().system_id(system_id), SuffixParams.get());
		
		 
	}
	
	
	@RequestMapping( value="/{system_id}",  method = RequestMethod.GET )
	public Object  getTicket ( @ModelAttribute( Global.session_key_user) User user ,
								  @PathVariable  String system_id){
		 
		return rest.https.get(user, TicketUrl.getbox, UrlParams.get().system_id(system_id), SuffixParams.get());
		
		 
	}
	
	
}
