package com.sunwayland.web.controller;

import java.util.HashMap;

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
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.PromiseUrl;
import com.sunwayland.web.vo.Global;

@Controller
@ResponseBody
@RequestMapping("permission")
//@SessionAttributes( "user")
public class PermissionController extends GenericAction {
	
	@Autowired
	public  ThingLinxRest  rest ; 
	

	/**
	 * 获得资源下的 所有用户组; 
	 */
	
	@RequestMapping(value="/region/{region_id}" , method= RequestMethod.GET)
	public Object getUserGroupOfRegion( 
			@ModelAttribute(Global.session_key_user) User user ,
			@PathVariable String  region_id 
			){
		//rest.https.get(user, permiss, urlParams, suffixParams);
		return rest.https.get(user, PromiseUrl.groupOfRegion, null,
						SuffixParams.get().put("region_id", region_id)
						);
		
		 
	} 
	
	
	@RequestMapping(value="/region/{region_id}/{group_id}" ,  method= RequestMethod.DELETE)
	public Object delUserGroupOfRegion( 
			@ModelAttribute(Global.session_key_user) User user ,
			@PathVariable String  region_id  ,
			@PathVariable String group_id 
			){ 
		
		return rest.https.delete(user, PromiseUrl.group_id_Path, null,
							UrlParams.get().group_id(group_id),
							SuffixParams.get().region_id(region_id) 
							);
		 
		 
	} 
	
	@RequestMapping(value="/region/{region_id}/{group_id}" , method= RequestMethod.POST)
	public Object addUserGroupOfRegion( 
			@ModelAttribute(Global.session_key_user) User user ,
			@PathVariable String  region_id ,
			@PathVariable String  group_id ,
			@RequestBody String[] permissions 
			){
		
		HashMap map = new HashMap();
		map.put("privilege", permissions);
		
		
		//rest.https.get(user, permiss, urlParams, suffixParams);
		return rest.https.put(user, PromiseUrl.group_id_Path, map,
						UrlParams.get().group_id(group_id),
						SuffixParams.get().region_id(region_id)
				); 
	} 
	
	
	@RequestMapping( value="/account/{group_id}" , method= RequestMethod.GET )
	public Object getAccountPromiseOFUserGroup (
			@ModelAttribute(Global.session_key_user) User user ,
			@PathVariable String group_id
			){
		
		return rest.https.get(user, PromiseUrl.group_id_Path , 
				UrlParams.get().group_id(group_id),
				SuffixParams.get().isaccount(true) 	);
		 
	} 
	
	@RequestMapping( value="/account/{group_id}" , method= RequestMethod.PUT )
	public Object setAccountPromiseOFUserGroup (
			@ModelAttribute(Global.session_key_user) User user ,
			@PathVariable String group_id,
			@RequestBody String[] promissions
			){
		
		
		HashMap map = new HashMap();
		map.put("privilege", promissions);
		
		return rest.https.put(user, PromiseUrl.group_id_Path, map,
				UrlParams.get().group_id(group_id),
				SuffixParams.get().isaccount(true)
		); 
		 
		 
	} 
	
	
}
