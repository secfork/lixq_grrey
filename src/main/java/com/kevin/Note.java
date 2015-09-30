package com.kevin;
 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.TTCCLayout;
import org.junit.Test;

import com.sunwayland.core.utils.Utils;

/*
功能:		web.duanxinwang.cc HTTP接口 发送短信

说明:		http://web.duanxinwang.cc/asmx/smsservice.aspx?name=登录名&pwd=接口密码&mobile=手机号码&content=内容&sign=签名&stime=发送时间&type=pt&extno=自定义扩展码
*/
public class Note {

     private  static String conncet = "短信验证码为：%s，请勿将验证码提供给他人。";
	
	/**
	 * system  联系人 短信验证码 发送; 
	 * @throws IOException 
	 */
	public  static String  send2Connect(String cell_phone ) throws IOException{
		// 消息模版:  短信验证码为：@，请勿将验证码提供给他人。【短信网】
		 
		 
 		String timeStr = Utils.getTimeStr( "yyyy-MM-dd hh:mm:ss",  1000*60*2 );
		
		 
		String  ss = "系统联系人短信验证码为：%s，请勿将验证码提供给他人!失效时间 :%s";
		String  num = Utils.randomNum(6);
		String  msg   = String.format( conncet , num , timeStr);
		
		tt( cell_phone ,msg);
		
		return num ; 
		
	}
	
	
	/**
	 * @param args
	 * @throws IOException
	 */ 
	private  static   String tt( String cell_phone , String  msg ) throws IOException {
		//发送内容
		String content =  msg; 
		String sign="ThingLinx";
		
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://web.duanxinwang.cc/asmx/smsservice.aspx?");

		
//		账号后台中心平台： http://120.26.60.42/a
//		name：jir@sunwayland
		
//		pwd 56585A6E9FC03990093C4B6D593F
		//  56585A6E9FC03990093C4B6D593F
		// 向StringBuffer追加用户名
		sb.append("name=jir@sunwayland");

		// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
		sb.append("&pwd=56585A6E9FC03990093C4B6D593F");

		// 向StringBuffer追加手机号码
		sb.append("&mobile="+cell_phone);

		// 向StringBuffer追加消息内容转URL标准码
		sb.append("&content="+URLEncoder.encode(content,"UTF-8"));
		
		//追加发送时间，可为空，为空为及时发送
		sb.append("&stime=");
		
		//加签名
		 sb.append("&sign="+URLEncoder.encode(sign,"UTF-8"));
		
		//type为固定值pt  extno为扩展码，必须为数字 可为空
		sb.append("&type=pt&extno=");
		
		// 创建url对象
		//String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
		
		System.out.println("sb:"+sb.toString());
		
		
		URL url = new URL(sb.toString());

		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");

		// 发送
		InputStream is =url.openStream();
		
		//转换返回值
		String returnStr = Note.convertStreamToString(is);
		
		// 返回结果为‘0，20140009090990,1，提交成功’ 发送成功   具体见说明文档
		//System.out.println(returnStr);
		// 返回发送结果

		return  returnStr ; 

	}
	/**
	 * 转换返回值类型为UTF-8格式.
	 * @param is
	 * @return
	 */
	private static String convertStreamToString(InputStream is) {    
        StringBuilder sb1 = new StringBuilder();    
        byte[] bytes = new byte[4096];  
        int size = 0;  
        
        try {    
        	while ((size = is.read(bytes)) > 0) {  
                String str = new String(bytes, 0, size, "UTF-8");  
                sb1.append(str);  
            }  
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                is.close();    
            } catch (IOException e) {    
               e.printStackTrace();    
            }    
        }    
        return sb1.toString();    
    }

}
