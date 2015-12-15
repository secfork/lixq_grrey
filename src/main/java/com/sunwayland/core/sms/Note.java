package com.sunwayland.core.sms;
 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.apache.log4j.TTCCLayout;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import com.sunwayland.core.utils.Utils;
import com.sunwayland.core.vo.webResult;
import com.sunwayland.web.vo.ErrCode;

/*
功能:		web.duanxinwang.cc HTTP接口 发送短信

说明:		http://web.duanxinwang.cc/asmx/smsservice.aspx?name=登录名&pwd=接口密码&mobile=手机号码&content=内容&sign=签名&stime=发送时间&type=pt&extno=自定义扩展码
*/
public class Note {

 	static Logger log =  Logger.getLogger(Note.class);
	
     static  String  msgBody = "您的验证码是：%s。请不要把验证码泄露给其他人并在15分钟内完成验证。如非本人操作，可不用理会！";
 	
	public static void sendSMS(HttpSession session, String cell_phone ,   
										String smskey , String timeoutkey)   {
		
		Object timeout = session.getAttribute( timeoutkey ); 
		if( null == timeout || (long) timeout < System.currentTimeMillis() ){
			
	 
			
			String  num = Utils.randomNum(6);
			
			String  msg   = String.format( msgBody , num );
			 
			Veesing.tt( cell_phone , msg );
			 
			session.setAttribute( smskey , num ); 
			// 短信显示有效时间是 2分钟; 
			session.setAttribute( timeoutkey,  System.currentTimeMillis() + 1000*60*15  ); 	
			
			
//			return  webResult.get().setRet(true);
		}else{
//			return webResult.get().setErr( ErrCode.send_repetition );
		}
	}
     
	
	/**
	 * system  联系人 短信验证码 发送; 
	 * @throws IOException 
	 */
	public  static String  send(String cell_phone   )    {
		// 消息模版:  短信验证码为：@，请勿将验证码提供给他人。【短信网】
		  
		  
		String  num = Utils.randomNum(6);
		
		String  msg   = String.format( msgBody , num );
		
//		kevin.tt( cell_phone ,msg);
		Veesing.tt( cell_phone , msg );
		
		return num ; 
		
	}
	
	
 
	
}
