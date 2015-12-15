package test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.config.RequestConfig;
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
		RequestConfig config = RequestConfig.custom().setConnectTimeout( 1000 )
				.setConnectionRequestTimeout(500).build();
	
		
		System.out.println(  config.getConnectionRequestTimeout());
		
	}
	
	public void eee(){ 
 
		
	}
	
}
