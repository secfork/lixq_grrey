package com.sunwayland.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.osgi.framework.AdminPermission;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.utils.Utils;
import com.sunwayland.rest.eneityV2.Account;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.AccountUrl;
import com.sunwayland.web.vo.Global;

@Controller
@ResponseBody
@RequestMapping( "account")
public class AccountController extends GenericAction  {
		
	
	/*
	 * 私有接口; 
	 */
	@RequestMapping(  method= RequestMethod.POST)
	public  Object  createAccount (    @Validated  @RequestBody Account account ,
										HttpServletRequest request ,
										HttpSession session,
										BindingResult result 
									){
		
		Utils.handlerBindngResult(result);
		 
		
		String identify = (String) session.getAttribute(Global.session_key_identifypic );
		
		// 国际化 信息 返回;  
		if( StringUtils.isBlank(identify)  || !identify.equalsIgnoreCase(account.getIdentifyCode())){
			// 验证码; 
			return   RESP_ERR(  "check.identif" );
		}
		
		 account.setAdd_alarm_space("true");
		 account.setIdentifyCode(null); 
		 
		return rest.http.post(AccountUrl.create,  account	,UrlParams.get(), SuffixParams.get());
		 
	}
	
}
