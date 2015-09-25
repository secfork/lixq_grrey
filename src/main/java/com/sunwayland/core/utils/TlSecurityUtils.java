package com.sunwayland.core.utils;

import org.apache.shiro.SecurityUtils;

public class TlSecurityUtils  extends SecurityUtils {
		
     public static Object getSessionAttr(String key){
    	  Object attribute = getSubject().getSession().getAttribute(key);   
    	  return attribute ;
	}
	
}
