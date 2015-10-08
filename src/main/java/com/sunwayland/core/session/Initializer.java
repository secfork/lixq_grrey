package com.sunwayland.core.session;

import org.springframework.data.redis.connection.jredis.JredisConnectionFactory;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;



public class Initializer  extends AbstractHttpSessionApplicationInitializer {
	
	 
	public Initializer() {     
		
		super(JredisConnectionFactory.class);
    }
 
	
}
