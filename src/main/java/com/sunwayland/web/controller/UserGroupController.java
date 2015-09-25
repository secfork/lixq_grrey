package com.sunwayland.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.core.vo.WebPage;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.eneityV2.UserGroup;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.UserGroupUrl;
import com.sunwayland.web.vo.Global;

@Controller
@ResponseBody
@RequestMapping("usergroup")
//@SessionAttributes("user")
public class UserGroupController extends GenericAction {

	@RequestMapping(method = RequestMethod.GET)
	public Object queryUserGroup(  
			@ModelAttribute(Global.session_key_user) User user, WebPage page) {

		return rest.https.query(user, UserGroupUrl.query,
				null,
				SuffixParams.get().calc_sum(true),
				SuffixParams.get().limit(page.getLimit()).offset(page.getOffset()));

	}


	@RequestMapping(method = RequestMethod.POST)
	public Object createUserGroup(@ModelAttribute(Global.session_key_user) User user, 
								 @RequestBody UserGroup group) {

		return rest.https.post(user, UserGroupUrl.create, group, null, null);

	}

	@RequestMapping(method = RequestMethod.PUT)
	public Object updateUserGroup(@ModelAttribute(Global.session_key_user) User user,
			@Validated(Update.class) @RequestBody UserGroup group
			) {
		return rest.https.put(user, UserGroupUrl.r_u_d_ById, group, UrlParams.get().group_id(group.getId()), null);

	}

	@RequestMapping(value = "/{group_id:[0-9]+}", method = RequestMethod.DELETE )
	public Object delUserGroupById(@ModelAttribute(Global.session_key_user) User user,
			@PathVariable String group_id
			) {

		return rest.https.delete(user, UserGroupUrl.r_u_d_ById, null, UrlParams.get().group_id(group_id), null);

	}

	@RequestMapping(value = "/{group_id:[0-9]+}", method = RequestMethod.GET)
	public Object getUserGroupById(@ModelAttribute(Global.session_key_user) User user,
			@PathVariable String group_id
			) {
		return rest.https.get(user, UserGroupUrl.r_u_d_ById, UrlParams.get().group_id(group_id), null);

	}

	@RequestMapping(value = "/{group_id:[0-9]+}/users", method = RequestMethod.GET)
	public Object   queryUserOfGropup(
			@ModelAttribute(Global.session_key_user) User user,
			@PathVariable String group_id  ,
			WebPage page
			) {

		return rest.https.get(user, UserGroupUrl.query_usersOfgorup, 
							UrlParams.get().group_id(group_id), null);
		
//		return rest.https.query(user, UserGroupUrl.query_usersOfgorup,
//				            UrlParams.get().group_id(group_id), 
//							SuffixParams.get().calc_sum(true),
//							SuffixParams.get().limit(page.getLimit()).offset(page.getOffset())
//							 );
//		
		
		
	}

	@RequestMapping(value = "/{group_id:[0-9]+}/{user_id:\\d+}",  method = RequestMethod.DELETE)
	public Object delUserFromGropup(@ModelAttribute(Global.session_key_user) User user, 
			@PathVariable String group_id ,
			@PathVariable String  user_id
			) {

		return rest.https.delete(user, UserGroupUrl.delOrAdd_usersOfgorup_byId,
				null,
				UrlParams.get().group_id(group_id).user_id(user_id),
				null);

	}

	@RequestMapping(value = "/{group_id:[0-9]+}/{user_id:\\d+}",   method = RequestMethod.PUT)
	public Object addUserToGropup(@ModelAttribute(Global.session_key_user) User user, 
			@PathVariable String group_id,
			@PathVariable String  user_id
			) {

		return rest.https.put(user, UserGroupUrl.delOrAdd_usersOfgorup_byId,
				null,
				UrlParams.get().group_id(group_id).user_id(user_id),
				null);

	}

}
