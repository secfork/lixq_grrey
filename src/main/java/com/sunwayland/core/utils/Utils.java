package com.sunwayland.core.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.sunwayland.core.exception.ValidateException;
import com.sunwayland.rest.eneityV2.User;

 

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Utils {
	 
    
	Logger  log =  Logger.getLogger(Utils.class); 
	
	//private   static final  Type  map =  new TypeToken< Map >(){}.getType() ;
	public  static final  Gson gson = new Gson() ; 
	
	 
	
 
	
	/**
	 * Md5 算法!!!
	 * @param str
	 * @return
	 */
	@Deprecated
	public static String  toSha256 (String str)  {
	   
	    if(null == str) return "";  
//	    String string = new Md5Hash(str).toString(); 
	  //  String string = new Sha256Hash( str ,"www.sunwayland.com").toString(); 
//	    return string ;   
	    
	    return null ;
	}
	
	 public static Gson createGson() {
	        GsonBuilder gb = new GsonBuilder();  
	        gb.setLongSerializationPolicy(LongSerializationPolicy.STRING ) ; //.setDateFormat(DateFormat.LONG);
	         
	        
	       
	         
//	         gb.registerTypeAdapter( java.util.Date.class, new DateDeserializer() ).setDateFormat(DateFormat.LONG);
//	        gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()  ).setDateFormat(DateFormat.LONG);
	        
	        Gson gson = gb.create();
	        
	       
	        
	        return gson;
	 }
	
	 
	 public static String  iso2utf (String s ){
		 try {
			return  new String( s.getBytes("iso-8859-1") ,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	 }
	 
	 public static String  utf2iso (String s ){
		 try {
			return  new String(s.getBytes("utf-8"), "iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return s ; 
	 }
	 
	
	/**
	 * 修改   station , devices , points  相应的    network , params , params 字符串 为map ; 
	 */

 

	/**
	 * resources 字符串 不许 出现  空格,  14 (account) ;
	 * @param resources
	 * @return
	 * @throws Exception 
	 */
	public static String inspectionResource(String resources)  {
		// TODO Auto-generated method stub
		 resources = StringUtils.trim(resources);
		
		 if(resources.equals(""))
			  return resources ; 
		
		 
		  // 不许有空格; 
		 boolean matches = resources.matches("[0-9,]+");
		 if(!matches){
			 throw new RuntimeException("更新resource的字符串不合法!");
		 }
		 
		 
	     resources = resources.replace("14,", "").replace(",14", ""); 
		
		return resources;
	}

	@Deprecated
	public static Object  getShiroSessionAttr(Subject subject, String sessionkey) {
		 	
		// return   subject.getSession().getAttribute(sessionkey);
		return null ;
	}

 

	/**处理 表单验证 ;  
	 */
	public static void handlerBindngResult(BindingResult... result) {
		// TODO Auto-generated method stub
		ArrayList<BindingResult> errs = new ArrayList<BindingResult>();
		
		boolean error = false ; 
		
		for( BindingResult br : result){
			 if ( br.hasErrors() ){
				 error = true ; 
				 errs.add(br);
			 }
		}
		
		if( error ){
			throw new ValidateException( errs );
		}
		
		
	}


	/**
	 * {ret:{a:{b:{c:{d:xx}}}}}   
	 * string2=  ret.a.b.c.d ;
	 *  
	 * @param string
	 * @param string2 
	 */
	 
	 
	@Deprecated
	public static User getCurrentUser() {
//		 Object attribute = SecurityUtils.getSubject().getSession().getAttribute(Global.session_key_user);
//		return (User) attribute;
		return null ; 
	}
	
	/**获得国际化 字符串;
	 * 需 webapplication  配置上
	 * org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver 或者其他 localresolver ; 
	 * @return
	 */
	public static String getLocalText ( HttpServletRequest request  , String per  , String... args ){
		 WebApplicationContext context =  RequestContextUtils.getWebApplicationContext(request);
		 Locale locale = RequestContextUtils.getLocale(request);
		  
		 String sss = context.getMessage( per , args, locale); 
		 return sss ; 
	}
	
  
	
	private static String  nom = "0123456789";
	private static String  nom1 = "0123456789qwertyuiopasdfghjklzxcvbnm";
	
	private static Random rd = new Random();
	
	public static  String  randomStr ( int size ){
		int i = 0 ; 
		StringBuffer buf = new StringBuffer();
		while (i++ <size) {
			buf.append(  nom1.charAt(rd.nextInt(36) )); 
			
		}
		return  buf.toString();
	}
	
	
	public static String  randomNum ( int  size ){
		int i = 0 ; 
		StringBuffer buf = new StringBuffer();
		while (i++ <size) {
			buf.append(  nom.charAt(rd.nextInt(10) )); 
			
		}
		return  buf.toString();
		 
		
	}

		/**
		 *  删除 system Picture ; 
		 * @param pciurl
		 */
	public static void delSystemPicture(String pciurl) {
		
		 
		
		
		
	}
	
	
	public static boolean isWinOs (){
		String os = System.getProperty("os.name");  
		  
		 return  os.toLowerCase().startsWith("win") ;
		
	}

 

	public static String getTimeStr(String format, int  offset) {
		// TODO Auto-generated method stub
		
		
		String format2 = DateFormatUtils.format(new Date( new Date().getTime()+ offset ),  format);
		
		return format2;
	}
	
	
	
}
