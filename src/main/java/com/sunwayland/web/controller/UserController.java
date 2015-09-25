package com.sunwayland.web.controller;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.google.gson.Gson;
import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.utils.Utils;
import com.sunwayland.core.vo.WebPage;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.basic.FieldEnum;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.UserUrl;
import com.sunwayland.web.vo.Global;

@Controller
@ResponseBody
@RequestMapping("user")
//@SessionAttributes("user")
public class UserController extends GenericAction {

	 private Logger log = Logger.getLogger(UserController.class);
	 
	 @Autowired
	 private Gson gson ; 

	
	@Autowired
	private ThingLinxRest rest;

	@RequestMapping(method = RequestMethod.GET) 
	public Object queryUsers(@ModelAttribute(Global.session_key_user) User user, WebPage page) {

		return rest.https.query(user, UserUrl.queryUser,
				null,
				SuffixParams.get().calc_sum(true),
				SuffixParams.get().limit(page.getLimit()).offset(page.getOffset() )	
								  .sorts(FieldEnum.create_time, false) 
				);

	}
	

	@RequestMapping(method = RequestMethod.POST) 
	public Object createUser(@ModelAttribute(Global.session_key_user) User user, 
			@RequestBody User _user) {

			 
		return rest.https.post(user, UserUrl.createUser, _user,  null,null);

	}

	@RequestMapping(method = RequestMethod.PUT) 
	public Object updateUser(@ModelAttribute(Global.session_key_user) User user, 
			                 @RequestBody User _user ) {

		return rest.https.put(user, UserUrl.r_d_u_UserById, _user, 
				UrlParams.get().user_id(_user.getId()),
						null);	 
		
		 
	}
	
	
	
	
	@RequestMapping(value="/{user_id:[0-9]+}" , method = RequestMethod.DELETE)
	
	public Object delUserById(@ModelAttribute(Global.session_key_user) User user,
			@PathVariable String user_id) {

		return rest.https.delete(user, UserUrl.r_d_u_UserById, null, 
					UrlParams.get().user_id( user_id),
					null);

	}
	

	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(
			@Valid @RequestBody User User,
//			@ModelAttribute(Global.session_key_user) User user,
			HttpServletRequest request,
			BindingResult result,
			HttpSession session,
			Locale loc) {
 

		Utils.handlerBindngResult(result);
		 
		 
		try {
			 
			
			log.info(" login  id  = " + session.getId());
			log.info(" login  id  = " + request.getSession().getId());

			Integer  times =   (Integer) session.getAttribute(Global.session_key_login_time); 
			         times = null ==times?0: times;
			String ver = User.getVerifi();
			
			if(null != ver &&  times > 2 ){
				String verifi = (String) session.getAttribute(Global.session_key_verifi);
				if (!ver.equalsIgnoreCase(verifi)) {
					return RESP_ERR("verifi_error");
				}
			}
			 
			// 登录 申请 accesskey; 
			rest.https.applyAccesskey(User);
			
			// 根据  username 获得用户; 
			Map usemap = (Map) rest.https.get(User, UserUrl.getByName,
					 null , 
					 SuffixParams.get().name(User.getUsername())
					).get("ret"); 
			
			//获取 account 权限;   // region权限, 在应用时获取;   

			// 更新最后 登录状态! 
			User obj = new User();
			obj.setLast_login_ip(request.getRemoteHost());
			obj.setLast_login_time(new Date().getTime()+"");
			rest.https.put(User, UserUrl.r_d_u_UserById,
					obj,
					UrlParams.get().user_id( usemap.get("id").toString()),
					null
					);
			
			 User.setAccount_id(usemap.get("account_id").toString());
			
			session.setAttribute(Global.session_key_user, User); 
			return RESP(usemap);

		} catch (Exception e) {
			e.printStackTrace();
			log.info("-- 登录失败--");
			// 记录登录失败次数; 超过三次 需要输入验证码;
			Integer times = (Integer) session.getAttribute(Global.session_key_login_time);
			times = null == times ? 0 : times;
			session.setAttribute(Global.session_key_login_time, ++times);

			return RESP_ERR("pass_error");
		}

	}
	
	
	// ======================登出======================================
	@RequestMapping(value = "/logout" , method=RequestMethod.GET)
	@ResponseBody
	public void logout( HttpSession session , SessionStatus sessionStatus) {
		
		session.invalidate();
		 
		sessionStatus.setComplete();
		//return RESP(true);

	}

	
	

}
