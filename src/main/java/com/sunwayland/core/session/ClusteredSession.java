package com.sunwayland.core.session;

import java.util.Set;

import org.springframework.session.ExpiringSession;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

public class ClusteredSession<S extends ExpiringSession> {

	 private SessionRepository<S> repository;
	 
	 public void demo() {
	        S toSave = repository.createSession(); 
	        // ...
	        toSave.setMaxInactiveIntervalInSeconds(30); 

	        repository.save(toSave); 

	        S session = repository.getSession(toSave.getId()); 
	        // ...
     }

}
