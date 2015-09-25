package test;

import java.util.HashMap;

import javax.crypto.Cipher;

import org.junit.Test;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;
import com.sunwayland.rest.basic.CallType;
import com.sunwayland.rest.eneityV2.User;

public class common {

	
	@Test
	public void t1 (){
		 
		User u1 = new User();
		
		System.out.println(  u1.hashCode()  );  // 14695349
		System.out.println(  u1.hashCode()  );  // 14695349
		
//		System.out.println(  Pattern.matches( ".*/(png|jpeg|jpg)$", "gaefa/jpgf"));
		
		HashMap m = new HashMap ();
		
		m.put("123",  CallType.ALL);
		
		System.out.println( m);
		
		Gson  g = new Gson();
		
		System.out.println( g.toJson(m) );
		;
		
		
	}
	
 
	
}
