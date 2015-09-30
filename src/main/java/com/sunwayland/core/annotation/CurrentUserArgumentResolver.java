package com.sunwayland.core.annotation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.sunwayland.web.vo.Global;

 

 

public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter ) {
      if (parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
	    
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
						          ModelAndViewContainer mavContainer, 
						          NativeWebRequest webRequest,
						          WebDataBinderFactory binderFactory) throws Exception {
		  
		 HttpServletRequest request  = (HttpServletRequest) webRequest.getNativeRequest();
	
		 Object attribute = request.getSession().getAttribute(Global.session_key_user);	  
		 
		 return attribute ; 
		  
		
	}

	 
	
	 


 
 
/*  @Override
    public Object resolveArgument(MethodParameter parameter, 
    							  ModelAndViewContainer mavContainer, 
    							  NativeWebRequest webRequest,
    							  WebDataBinderFactory binderFactory) throws Exception {
        CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
        return webRequest.getAttribute(currentUserAnnotation.value(), NativeWebRequest.SCOPE_REQUEST);
    }
*/
   
}
