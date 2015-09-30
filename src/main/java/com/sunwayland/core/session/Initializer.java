package com.sunwayland.core.session;

import javax.servlet.ServletContext;
import javax.swing.Spring;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;



public class Initializer  extends AbstractHttpSessionApplicationInitializer {
	
	 
	public Initializer() {   
		super(Config.class);
    }

 
	 
	
	
}
