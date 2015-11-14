package com.sunwayland.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.vo.WebPage;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.eneityV2.Role;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.RoleUrl;
import com.sunwayland.web.vo.Global;


@Controller
@ResponseBody
@RequestMapping("role")
//@SessionAttributes("user")
public class RoleController  extends GenericAction{

	
	@Autowired
	public  ThingLinxRest  rest ; 
	
	
	
	@RequestMapping( method=RequestMethod.POST)
	public Object createrole( @ModelAttribute(Global.session_key_user) User user ,
							@RequestBody Role role 
				){
		
		return rest.https.post(user, RoleUrl.create, role, null, null);
		
	}
	
	
	@RequestMapping( method=RequestMethod.GET)
	public  Object getAllRole( @ModelAttribute(Global.session_key_user) User user  ){
		return    rest.https.get(user, RoleUrl.query, null, null);
		 
	}
	
	@RequestMapping(value="/{role_id:[0-9]+}", method=RequestMethod.DELETE)
	public Object delRoleById(  @ModelAttribute(Global.session_key_user) User user ,
								@PathVariable String  role_id 
				){	
		return rest.https.delete(user, RoleUrl.r_u_d_byId, null, UrlParams.get().role_id(role_id) , null);	
	}
	
	@RequestMapping(value="/{role_id:[0-9]+}", method=RequestMethod.GET)
	public Object getRoleById( @ModelAttribute(Global.session_key_user) User user ,
				@PathVariable String  role_id 
				){ 
		
		return rest.https.get(user, RoleUrl.r_u_d_byId, UrlParams.get().role_id(role_id), null);		
	}
	
	@RequestMapping(value="/{role_id:[0-9]+}", method=RequestMethod.PUT)
	public Object updateRoleById(	@ModelAttribute(Global.session_key_user) User user ,
									@PathVariable String  role_id  ,
									@RequestBody Role role
				){
		
		return rest.https.put(user, RoleUrl.r_u_d_byId, role, UrlParams.get().role_id(role_id), null);
		
	}
	
	
}
