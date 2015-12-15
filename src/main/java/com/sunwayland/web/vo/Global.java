package com.sunwayland.web.vo;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.sunwayland.rest.eneityV2.User;


/**
 * verify 短信验证 前缀,
 * 
 * idenfity  ; 图片验证码 前缀; 
 * 
 * @author Administrator
 *
 */
public class Global {
//123  
	//1234  111111111
    public static final String session_key_user        = "user";
    public static final Class  rest_return_type        = String.class;
    public static final String session_key_author      = "author";
    
    public static final String session_key_uiauthor    = "uiauthor";
    public static final String session_key_login_time  = "logtimes";
    
    /**
     * system 联系人验证码!
     */
    public static final String verifiy_connect ="connect_verifiy";
	// systen  联系人 验证码 短信失效时间;
	public static String verify_connect_timeout = "connect_timeout";
	
	// account 注册是 手机验证码, 失效时间 key ; 
	public static String verify_account = "account_verify" ;
	public static String verify_account_timeout = "account_timeout" ;
    
    
    
    
   
    public static final String identifypic_login = "identifypic";
     
    
    
    public static final String session_key_projids = "projids";
    
      
	public static Type mapType =   new TypeToken< Map >(){}.getType();
	public static String STATION_STATE_ACTIVE = "1";
	public static String STATION_STATE_UNACTIVE = "0";
	
	public static Integer max_ItemsPerPage = 1000 ;  // 每页 最多条数;
	public static String hold_account_name  = "hold_admin"; // 更改admin 用户是, 缓存老admin ;
	public static String verify_updateadmin_timeout = "verify_updateadmin_timeout";
	public static String verify_update_admin = "verifyu_update_admin";
	public static String hold_admin_phone = "hold_admin_phone";
	public static String identifypic_update_admin = "identifypic_update_admin";
	public static String identifypic_create_account = "identifypic_create_account";
	
	public static String hold_account ="hold_account"; // account 注册;
	//public static String hold_account_token = "hold_account_token"; // 创建account , 发动到邮 确保从一邮件连接到登录界面的 uuid ; 
	public static String session_key_usermap = "session_key_usermap";
	public static String session_logout = "session_logout";
	

	
	public static  final String err_key ="err";
	public static  final String ret_key ="ret";
	
  
    
    /**  0--dtu Server   1--box  2--tcp client    station类型定义   */
     
    
    
}
