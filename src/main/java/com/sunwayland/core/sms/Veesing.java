package com.sunwayland.core.sms;

import java.io.IOException;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sunwayland.core.exception.ExceptionMessage;

public class Veesing {

	static Logger  log  = Logger.getLogger(Veesing.class);
	
	private static  HttpClient  client = new HttpClient();
	private static String Url = "http://121.199.16.178/webservice/sms.php?method=Submit";
	
	public static  String  tt( String  cell_phone ,  String content ){
		PostMethod method = new PostMethod(Url);
		client.getParams().setContentCharset("UTF-8");
		
		
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		
		 
		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", "cf_thinglinx"), 
			   // 377f7cd17910c0620dea5ae4d1cbce86 ===  thinglinx@123
			   // new NameValuePair("password", "thinglinx@123"), //密码可以使用明文密码或使用32位MD5加密
 			    
			    new NameValuePair("password", "377f7cd17910c0620dea5ae4d1cbce86"),
			    new NameValuePair("mobile", cell_phone ), 
			    
			    new NameValuePair("content", content ),
		};
		
		method.setRequestBody(data);		
				
		
		try {
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
					 
			
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
			
			
			String code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			
//			log.info(" content = " + content);
//			log.info(code);
			log.info(msg);
//			log.info(smsid);
						
			if("2".equals(code) ){
				
				System.out.println("短信提交成功");
				log.info("发送短信 : " + cell_phone +" : smsid :  " + smsid );
				 
			}else{
				log.error( cell_phone + " : err : "+msg );
				throw new ExceptionMessage(  msg  );
			}
			
	 
			
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 
		return null ; 
	}
	
}
