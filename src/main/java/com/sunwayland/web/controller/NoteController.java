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

import com.kevin.Note;
import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.web.vo.ErrCode;
import com.sunwayland.web.vo.Global;

  
@Controller
@ResponseBody
@RequestMapping("note")
public class NoteController extends GenericAction  {
	
	 
	  
	/* "connect" ; system 联系人设置时 手机验证;
	 * "account" ; account 注册是 手机验证; 
	 * "admin"   ; account 更改admin 时 手机验证; 
	 *  
	 */
	
	@RequestMapping( value="/{usedfor}" , params={"mobile_phone"}, method=RequestMethod.GET)
	public  Object sendConnect(  
							HttpSession session , 
							@PathVariable String usedfor ,
							@RequestParam( ) String mobile_phone
							)  { 
		   
		if( ! mobile_phone.matches("^1\\d{10}$") ){
			return RESP_ERR( ErrCode.phone_pattern_err );
		}
		
		try {
			// system  联系人 号码验证; 
			if(  StringUtils.equalsIgnoreCase(usedfor, "connect")){ 
				return sendSMS(session, mobile_phone , Note.conncet , Global.verifiy_connect , Global.verify_connect_timeout); 
			}
			
			// account 注册是 手机 验证; 
			if(StringUtils.equalsIgnoreCase(usedfor, "account")){ 
				 return  sendSMS(session, mobile_phone, Note.account, Global.verify_account, Global.verify_account_timeout);
			}
			 
			// admin 更改是; 找回密码;  
			if(StringUtils.equalsIgnoreCase(usedfor, "admin")){ 
				Object str =    session.getAttribute(Global.hold_admin_phone);
				if( null !=str ){
					return sendSMS(session, (String)str, Note.admin, Global.verify_update_admin, Global.verify_updateadmin_timeout);
				}  
			}
			 
			// user 手机验证; 手机号为  session key ,  验证码为 alue; 
			if( StringUtils.equalsIgnoreCase(usedfor, "user")){ 
				 
				String number = Note.send(mobile_phone, Note.user);
				session.setAttribute(mobile_phone, number ); 
				
				return RESP(true);
			}
			
		}catch( Exception e ){
			return RESP_ERR(ErrCode.sms_error);
		}
		
		 
		
		return RESP_ERR( ErrCode.unknown );
		
		
		 
	}

	private Object sendSMS(HttpSession session, String mobile_phone , String SMSbody , String smskey , String timeoutkey) throws IOException {
		
		Object timeout = session.getAttribute( timeoutkey ); 
		if( null == timeout || (long) timeout < System.currentTimeMillis() ){
			
			String num = Note.send( mobile_phone ,  SMSbody ); 
			session.setAttribute( smskey , num ); 
			// 短信显示有效时间是 2分钟; 
			session.setAttribute( timeoutkey,  System.currentTimeMillis() + 1000*60*2  ); 		
			return RESP(true);
		}else{
			return RESP_ERR(ErrCode.send_repetition );
		}
	}
	 
	

	
}
