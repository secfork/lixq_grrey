package com.sunwayland.core.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.sunwayland.web.vo.Global;

public class AuthorMapArgumentResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		 if (parameter.hasParameterAnnotation(AuthorMap.class)) {
	            return true;
	        }
	        return false; 
		 
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		Object object = SecurityUtils.getSubject().getSession().getAttribute(Global.session_key_author);
		 
		 return object ; 
	}

}
