package com.sunwayland.core.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

 
/**
 *  恶意重复提交 过滤; 
 *  @author Administrator
 *  
 * 
 *  
 */


public class RegistFilter  implements Filter   {
   
	  private  Logger log = Logger.getLogger(RegistFilter.class);
	  
	@SuppressWarnings("rawtypes")
	private  ArrayList  ipset = new ArrayList<>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		      ipset.contains(""); 
	}

	@Override
	public void doFilter( ServletRequest request, ServletResponse response,
							FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request ;
		
	     log.info( "来访ip = "+request.getRemoteAddr() +" ---" + req.getRequestURL() );
	      
	     log.info( "来访 session id = "+ request.getClass() );
		
	     //ServletActionContext.getRequest().getSession();
	      
	   
	     
	  /*   if( ipset.contains(remoteHost) &&  null == req.getSession().getAttribute(Global.session_key_user)  ){
	         
	       //  new Subject.Builder().principals("12").authenticated(true/false).buildSubject()
	         Builder builder = new Subject.Builder();
	         Subject subject = SecurityUtils.getSubject();
	         AthenaShiroToken token = new AthenaShiroToken(); 
		     token.setUsername("admin");
		     token.setPassword("admin".toCharArray());
		     token.setComp_name("力控元通");
		     token.setRememberMe(true);
		     subject.login(token);
		     token.setRememberMe(true);
		     subject.login( token );
		     
		     User a = new User(); 
		     a.setCompId(1L);
		     
		     session.setAttribute(Global.session_key_user,a);
	     }
	     */
      

			
		chain.doFilter(request, response);
		 
		
	}

	@Override
	public void destroy() {
		
	}
	 
}
