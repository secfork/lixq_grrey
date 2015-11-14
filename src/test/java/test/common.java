package test;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.aspectj.ajde.ui.swing.GoToLineThread;
import org.junit.Test;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;
import com.kevin.Note;
import com.sunwayland.rest.basic.CallType;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.web.vo.Global;

public class common {

	
	@Test
	public void t1 () throws IOException{ 
		
		Gson g = new Gson() ;
		String s = "{err=null, ret=[{online=0, ts=1.447445818289E12}, {online=0, ts=1.447445818243E12}, {online=0, ts=1.447445818244E12}, {online=0, ts=1.447445818244E12}, {online=0, ts=1.447445820502E12}, {online=0, ts=1.447445818244E12}, {online=0, ts=1.447445820501E12}, {online=0, ts=1.447445818243E12}, {online=0, ts=1.447445818244E12}, {online=0, ts=1.447445818245E12}, {online=0, ts=1.447445830208E12}, {online=0, ts=1.447445828989E12}, {online=0, ts=1.447445817033E12}, {online=0, ts=1.447445816872E12}, {online=0, ts=1.447445815211E12}, {online=0, ts=1.447445818244E12}, {online=0, ts=1.447445859278E12}, {online=0, ts=1.447445856388E12}, {online=0, ts=1.447445820548E12}, {online=0, ts=1.447445816868E12}]}";
	
		 
		
	  for (int i = 0; i < 100; i++) {
		  long start = System.currentTimeMillis();
		  
		  Map  map = g.fromJson(s,Global.mapType);
		  
		   System.out.println(  map.get("ret")  );
		  
		  long ed  = System.currentTimeMillis();
		  System.out.println( "====="+(ed- start)+"===");

	  }	
		
	  
	  
	}
	
 
	
}
