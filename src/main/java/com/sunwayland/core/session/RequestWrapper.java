package com.sunwayland.core.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class RequestWrapper extends HttpServletRequestWrapper {

	public RequestWrapper(HttpServletRequest request) {
		super(request);	 
	}

	@Override
	public HttpSession getSession(boolean create) {
		// TODO Auto-generated method stub
		return super.getSession(create);
	}

	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return super.getSession();
	}

	
	
	
	
}
