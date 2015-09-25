package com.sunwayland.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.utils.Utils;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.web.vo.Global;

  
@Controller
@ResponseBody
@RequestMapping("note")
public class NoteController extends GenericAction  {
	
	 
	
	//RandPicture  
	
	@RequestMapping( value="/send_connnect" , params={"mobile_phone"}, method=RequestMethod.GET)
	public  Object sendNote( @ModelAttribute(Global.session_key_user) User user ,
							HttpSession session ,
							String mobile_phone) throws IOException{
		
		String  num = Utils.randomNum(6);
		
		session.setAttribute(Global.session_key_connect_verifiy, num ); 
		
		if(null!=mobile_phone && mobile_phone.matches("^1\\d{10}$") ){
		//	Note.send2Connect( mobile_phone ,num);
			return RESP(true);
		}else{
			return  RESP(false);
		}
		
	  
		 
	}
	
	
	
	/**
	 * 联系人验证码验证; 
	 * @return
	 */
	@RequestMapping( value="/verify_connect" , params = "{code}" ,  method = RequestMethod.GET )
	public Object verfiConnectVerify (  String code , HttpSession session ){
		Object attribute = session.getAttribute(Global.session_key_connect_verifiy);
		if(null == attribute){
			return  RESP_ERR(true);
		}
		String sk = (String) attribute; 
		
		return ( sk.equalsIgnoreCase(code));
	}
	
}
