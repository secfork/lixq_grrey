package test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sunwayland.core.testsupport.MvcTestSupport;
import com.sunwayland.rest.ThingLinxRest;

public class MvcTest  {
	
	Log logger = LogFactory.getLog(MvcTest.class);
	
	@Autowired
	private  ThingLinxRest rest ;
	
	@Test
	public void t1 () throws Exception{  
		// String  s = String.format("roleId:%s not exist", "xx");
		System.out.println(  "4123gae/png".matches("\\*+[jpeg,png,jpg]$"));
	}
	
	public void eee(){ 
 
		
	}
	
}
