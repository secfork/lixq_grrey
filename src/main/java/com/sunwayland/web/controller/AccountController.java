package com.sunwayland.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javassist.bytecode.analysis.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.utils.SimpleMail;
import com.sunwayland.core.utils.Utils;
import com.sunwayland.core.validate.type.Create;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.eneityV2.Account;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.AccountUrl;
import com.sunwayland.web.vo.ErrCode;
import com.sunwayland.web.vo.Global;

@Controller
@ResponseBody
@RequestMapping("account")
public class AccountController extends GenericAction {
	
	Logger  log = Logger.getLogger(AccountController.class);
	@Autowired
	public  ThingLinxRest  rest ; 
	

	/*
	 * 私有接口;
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST)
	public Object createAccountSetp1(@Validated(Create.class)
				@RequestBody Account account,
			HttpServletRequest request,
			BindingResult result
			) {

		Utils.handlerBindngResult(result);

		String idenfity = account.getIdentifyCode();

		HttpSession session = request.getSession();

		Object attribute = session.getAttribute(Global.identifypic_create_account);
		if (null == attribute)
			return null;

		if (!StringUtils.equalsIgnoreCase(idenfity, (String) attribute)) {
			return RESP_ERR(ErrCode.identify_err);
		}

		HashMap body = new HashMap();
		body.put("account", account.getName());

		// 验证 accountname ;
		Map resp = rest.http.post("/signin/verify/account", body, null, null);
		if (null != resp.get(Global.err_key)) {
			return resp;
		}

		// 验证  激激活吗 ;
		body.clear();
		body.put("invitation_code", account.getInvitation_code());
		Map resp2 = rest.http.post("/signin/verify/code", body, null, null);
		if (null != resp2.get(Global.err_key)) {
			return resp2;
		}

		// 发送邮件;

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		Object web_site = context.getBean("web_site");
		String email = account.getAdmin().getEmail();

		
		String uuid  =  Utils.randomStr(32);
		String sid = session.getId();
		 
		String registurl = web_site + "/#/access/signup?v=" + uuid+"&s=" + sid ;

		try {
			SimpleMail.sendAccountRegistEmail(email, registurl);
		} catch (Exception e) {
		    log.info(e);
			return RESP_ERR(ErrCode.send_maill_err);
		}

		// 发送邮件成功
		session.setAttribute(Global.hold_account, account);
		
		// uuid 为 key  ,  时间为 value; 
		session.setAttribute( uuid , new Date().getTime() );

		return RESP(true);

	}

	@RequestMapping(value = "/admin", params = { "uuid" }, method = RequestMethod.POST)
	public Object createAccountStep2(@Validated(Create.class) @RequestBody User user,
			HttpServletRequest request,
			String uuid,
			BindingResult result
			) {
		Utils.handlerBindngResult(result);

		HttpSession session = request.getSession();

		Object attribute = session.getAttribute(uuid);

		// 页面有效;
		if (null == attribute)
			return RESP_ERR(ErrCode.page_expire);

		
		long  sendEmailTime = (long) attribute;
		
		//30 分钟有效; 
		if( sendEmailTime  + 1000*60*30 <  new Date().getTime()  ){
			return   RESP_ERR(ErrCode.page_expire);
		}
		
		
		// 创建 account , 附带admin ;
		Object account = session.getAttribute(Global.hold_account);
		if (null == account)
			return RESP_ERR(ErrCode.page_expire);

		Account comp = (Account) account;

		User admin = comp.getAdmin();

		admin.setUsername(user.getUsername());
		admin.setPassword(user.getPassword());
		
		admin.setEmail_verified(true);
		
		
		comp.setAdd_alarm_space("true");
		comp.setIdentifyCode(null);

		Map post = rest.http.post(AccountUrl.create, account, null, null);
		
		session.removeAttribute(uuid);
		session.removeAttribute(Global.hold_account);
		
		return post;

	}

	// account admin 页面 是否有效;
	@RequestMapping(value = "/admin", params = { "uuid" }, method = RequestMethod.GET)
	public Object isUUID_Exist(HttpServletRequest request, String uuid) {
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute(uuid);
		return RESP(null != attribute);
	}

	@RequestMapping(params = { "account_id" }, method = RequestMethod.GET)
	public Object getAccountById(
			@ModelAttribute(Global.session_key_user) User user,
			String account_id
			) {
		return rest.http.get("/accounts/{account_id}", UrlParams.get().account_id(account_id), 
				null);

	}

}
