package com.sunwayland.web.vo;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.sunwayland.rest.eneityV2.User;

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
    public static final String session_key_connect_verifiy ="connect_verifiy";
    
    /**
     * 
     * 注册是验证码
     */
    public static final String session_key_identifypic = "identifypic";
    public static final String session_key_verifi = "verifi";
    
    
    public static final String session_key_projids = "projids";
    
     
    public static User adminUser = new User();
	public static Type mapType =   new TypeToken< Map >(){}.getType();
	public static String STATION_STATE_ACTIVE = "1";
	public static String STATION_STATE_UNACTIVE = "0";
	
	public static Integer max_ItemsPerPage = 1000 ;  // 每页 最多条数;
	
	// systen  联系人 验证码 短信失效时间;
	public static String session_connect_timeout = "connect_timeout";
	
	public static  final String err_key ="err";
	public static  final String ret_key ="ret";
	
    {
	adminUser.setUsername("admin");
	adminUser.setPassword("admin");
    }

    
    /**  0--dtu Server   1--box  2--tcp client    station类型定义   */
     
    
    
}
