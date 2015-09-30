package com.sunwayland.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kevin.Note;
import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.web.vo.Global;

  
@Controller
@ResponseBody
@RequestMapping("note")
public class NoteController extends GenericAction  {
	
	 
	
	//RandPicture  
	
	@RequestMapping( value="/connnect" , params={"mobile_phone"}, method=RequestMethod.GET)
	public  Object sendConnect( @ModelAttribute(Global.session_key_user) User user ,
							HttpSession session ,
							String mobile_phone) throws IOException{
		
		 
		
		if(null!=mobile_phone && mobile_phone.matches("^1\\d{10}$") ){
			
			String num = Note.send2Connect( mobile_phone );
			session.setAttribute(Global.session_key_connect_verifiy, num ); 
			// 短信显示有效时间是 2分钟;  这里实际是 2.5 分钟; 
			session.setAttribute(Global.session_connect_timeout,
					             new Date().getTime() + 1000*60*2.5);
			
			
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
	public Object verfiConnect (  String code , HttpSession session ){
		Object attribute = session.getAttribute(Global.session_key_connect_verifiy);
		if(null == attribute){
			return  RESP_ERR(true);
		}
		String sk = (String) attribute; 
		
		return ( sk.equalsIgnoreCase(code));
	}
	
}
