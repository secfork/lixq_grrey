package com.sunwayland.core.shiro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.web.vo.Global;

/**
 * 用户身份验证,授权 Realm 组件
 * 
 * @author StarZou
 * @since 2014年6月11日 上午11:35:28
 **/ 
 
public class ThingLinxSecurityRealm extends AuthorizingRealm {
 
 
    
    
    
    
    private RetryLimitMatcher  limitMatcher ; 
    
    private  Logger log =  Logger.getLogger(ThingLinxSecurityRealm.class);
    
    /**
     * 权限检查
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	
       	log.debug(" ===========权限检查 =======");
    	  
        
    	 
        Session session = SecurityUtils.getSubject().getSession(); 
    	
    	
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        
        User  user =  (User)  session.getAttribute(Global.session_key_user);
          
    
         
         // 添加权限集合;
         authorizationInfo.addRole("*");
          
          
        return authorizationInfo;
    }

    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	 
    	  String username = String.valueOf(token.getPrincipal());
          String password = new String((char[]) token.getCredentials());
          
          SimpleAuthenticationInfo  auther  = new SimpleAuthenticationInfo(); 
          
          return auther ; 
     
     
    }

}
