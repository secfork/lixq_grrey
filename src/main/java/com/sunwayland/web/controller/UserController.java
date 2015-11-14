package com.sunwayland.web.controller;

import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javassist.bytecode.analysis.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.utils.SimpleMail;
import com.sunwayland.core.utils.Utils;
import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.core.validate.type.VerifyEmail;
import com.sunwayland.core.validate.type.VerifyPhone;
import com.sunwayland.core.vo.WebPage;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.UserUrl;
import com.sunwayland.web.vo.ErrCode;
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
	public  ThingLinxRest  rest ; 
	
	 
	 

	 

	@RequestMapping(method = RequestMethod.GET) 
	public Object queryUsers(@ModelAttribute(Global.session_key_user) User user, WebPage page) {

		return rest.https.query(user, UserUrl.queryUser,
				null,
				SuffixParams.get().calc_sum(true),
				SuffixParams.get().limit(page.getLimit()).offset(page.getOffset() )	
								  .sortDESC_ByCreateTime()
				);

	}
	

	@RequestMapping(method = RequestMethod.POST) 
	public Object createUser(@ModelAttribute(Global.session_key_user) User user, 
			@Validated( Create.class)@RequestBody User _user ,
			String[] groupids ,
			BindingResult result
			) {
		
		Utils.handlerBindngResult(result);
		
		SuffixParams suffixParams = SuffixParams.get();
		for( String gid: groupids){
			suffixParams.group_id(gid);
		} 
		return rest.https.post(user, UserUrl.createUser, _user,  null,suffixParams);

	}
	
	
	@RequestMapping( value="/groups" , method= RequestMethod.GET ,  params={"user_id"})
	public Object getGroupsOfUser ( @ModelAttribute( Global.session_key_user) User user ,
					String user_id 
			){ 
		
		return rest.https.get(user, "/users/{user_id}/groups",   UrlParams.get().user_id(user_id), null);
		 
	}
	

	@RequestMapping(method = RequestMethod.PUT) 
	public Object updateUser(@ModelAttribute(Global.session_key_user) User user, 
			                 @Validated(Update.class)@RequestBody User _user , 
			                 BindingResult result 
			) {

		Utils.handlerBindngResult(result);
		
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
	
	 @RequestMapping(value="/{user_id:[0-9]+}" , method = RequestMethod.GET)
	 public Object  getUserByid( @ModelAttribute(Global.session_key_user) User user,
			 	@PathVariable String user_id
			 ){
		 	// get {BASE_URL}/users/user_id?accesskey={accesskey}
		return  rest.https.get(user, "/users/{user_id}", UrlParams.get().user_id(user_id), null);
		 
		   
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
			   

			Integer  times =   (Integer) session.getAttribute(Global.session_key_login_time); 
			         times = null ==times?0: times;
			String ver = User.getVerifi();
			
			Object identy =  session.getAttribute(Global.identifypic_login);
			 
			if( null != identy || null != ver ||  times > 2 ){ 
				 
				if ( StringUtils.isBlank(ver) 
					|| null == identy
					||  ! StringUtils.equalsIgnoreCase(ver, (String)identy)
					) {
					return RESP_ERR( ErrCode.identify_err );
				}
			}
			 
			// 登录 申请 accesskey;  
			rest.https.applyAccesskey(User , request.getRemoteAddr() ); 
			
			// 根据  username 获得用户; 
			Map usemap = (Map) rest.https.get(User, UserUrl.getByName,
					 null , 
					 SuffixParams.get().name(User.getUsername())
					).get("ret"); 
			
			//获取 account 权限;   // region权限, 在应用时获取;    
			
			 User.setAccount_id(usemap.get("account_id").toString());
			 User.setId(usemap.get("id").toString());
			
			session.setAttribute(Global.session_key_user, User); 
			session.removeAttribute(Global.identifypic_login);
			return RESP(usemap);

		} catch (Exception e) {
			
			
			
			e.printStackTrace();
			log.info("-- 登录失败--");
			// 记录登录失败次数; 超过三次 需要输入验证码;
			
			Integer times = (Integer) session.getAttribute(Global.session_key_login_time);
			times = null == times ? 0 : times;
			session.setAttribute(Global.session_key_login_time, ++times);  
			return RESP_ERR(  ErrCode.pass_err );
			
			 
			
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

	@RequestMapping( value="sendverifyemail" , method= RequestMethod.POST )
	public  Object sendVerifyEmail (
				HttpServletRequest request,
				@ModelAttribute(Global.session_key_user) User user,
				@Validated( VerifyEmail.class) @RequestBody User _user ,
				BindingResult result 
			){
		
		Utils.handlerBindngResult(result);
		
		HttpSession session = request.getSession();
				
		// url 中 附带 ;   uuid  ={ user }; 
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		Object web_site = context.getBean("web_site");
		
		String email = _user.getEmail();
		 

		String uuid = UUID.randomUUID().toString();
		String verifyEmailUrl = web_site + "/#/access/verify_email?uuid=" + uuid ;

		_user.setSendVerifyEmailTime(new Date().getTime());
		session.setAttribute(uuid	, _user );
		
		//"请确认您的邮箱，只差一步，您的注册就成功了！", "请在30分钟内完成", "完成注册"
		try {
			SimpleMail.sendMail( _user.getEmail() , "用户邮箱验证", "请确认您的邮箱", "请在30分钟内完成", "确认", verifyEmailUrl );
		} catch (Exception e) {
			return  RESP_ERR(ErrCode.send_maill_err);
			
		}
		 
		return RESP(true) ; 
	}
	
	
	@RequestMapping( value="/verifyemail" , method= RequestMethod.GET)
	public Object verifyEmail ( 
				HttpServletRequest request,
				@ModelAttribute(Global.session_key_user) User user,
				String uuid  
			){
		
		HttpSession session = request.getSession();
		
		Object attribute = session.getAttribute(uuid);
		
		if( null == attribute){
			return RESP(false); 
		}
		 
		User  u = (User) attribute;
		
		long sendVerifyEmailTime = u.getSendVerifyEmailTime();
		
		if( sendVerifyEmailTime + 1000*60*30 < new Date().getTime()){
			return RESP(false);
		}
		
		u.setEmail_verified(true);
		u.setSendVerifyEmailTime(null); 
		
		Map map = rest.https.put(user, UserUrl.r_d_u_UserById, u, UrlParams.get().user_id(u.getId()), null );
		
		session.removeAttribute(uuid);
		
		return  RESP(true) ;
		
	 
	}
	
	@RequestMapping( value="/verifyphone" , method= RequestMethod.POST)
	public Object verifyMobilPhone ( @ModelAttribute(Global.session_key_user) User user, 
			HttpServletRequest request , 
			@Validated( VerifyPhone.class )@RequestBody User _user ,
			BindingResult result

			){
		
		Utils.handlerBindngResult(result); 
		
		HttpSession session = request.getSession();
		 
		Object attribute = session.getAttribute(_user.getMobile_phone());
		// 手机好是否在 session中;
		if(null == attribute){
			return  RESP_ERR(ErrCode.phone_err);
		}
		
		// 验证码是否正确; 
		if( ! StringUtils.equalsIgnoreCase(_user.getVerifi(), (String) attribute ) ){
			return  RESP_ERR(ErrCode.sms_verify_err);
		}
		
		// 更新 user ;
		_user.setMobile_phone_verified(true);
		
		
		return rest.https.put(user, UserUrl.r_d_u_UserById, _user , 
								UrlParams.get().user_id(_user.getId()), null);
		
		// SimpleMail.sendSimpleMail(email, "用户邮箱验证", title, note, message);
		
		   
	}
	
	

}
