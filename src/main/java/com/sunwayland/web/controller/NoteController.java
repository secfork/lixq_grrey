package com.sunwayland.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Path.Node;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunwayland.core.JsOrder.JsOrder;
import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.sms.Note;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.web.vo.ErrCode;
import com.sunwayland.web.vo.Global;

@Controller
@ResponseBody
@RequestMapping("note")
public class NoteController extends GenericAction {

	/*
	 * "connect" ; system 联系人设置时 手机验证; "account" ; account 注册是 手机验证; "admin" ;
	 * account 更改admin 时 手机验证;
	 */

	@RequestMapping(value = "/{usedfor}", params = { "mobile_phone" }, method = RequestMethod.GET)
	public Object sendConnect(
			HttpSession session,
			@PathVariable String usedfor,
			@RequestParam() String mobile_phone
			) {

		if (!mobile_phone.matches("^1\\d{10}$")) {
			return RESP_ERR(ErrCode.phone_pattern_err);
		}

		// system 联系人 号码验证;
		if (StringUtils.equalsIgnoreCase(usedfor, "connect")) {
			Note.sendSMS(session, mobile_phone, Global.verifiy_connect, Global.verify_connect_timeout);
		}

		// account 注册是 手机 验证;
		if (StringUtils.equalsIgnoreCase(usedfor, "account")) {
			Note.sendSMS(session, mobile_phone, Global.verify_account, Global.verify_account_timeout);
		}

		// admin 更改是; 找回密码;
		if (StringUtils.equalsIgnoreCase(usedfor, "admin")) {
			Object str = session.getAttribute(Global.hold_admin_phone);
			if (null != str) {
				Note.sendSMS(session, (String) str, Global.verify_update_admin, Global.verify_updateadmin_timeout);
			}
		}

		// user 手机验证; 手机号为 session key , 验证码为 alue;
		if (StringUtils.equalsIgnoreCase(usedfor, "user")) {
			Object attribute = session.getAttribute(Global.session_key_user);
			if (null == attribute) {
				return RESP_ERR(JsOrder.login);
			}

			String number = Note.send(mobile_phone);
			session.setAttribute(mobile_phone, number);

		}
		return RESP(true);

	}

}
