package com.sunwayland.web.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.web.vo.Global;


@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@ResponseBody
@RequestMapping("/driver") 
//@SessionAttributes("user")
public class DriverController  extends GenericAction{
	
	private  Logger log = Logger.getLogger(DriverController.class);
 
	// dtuDrivers    dtu驱动;    //配置采集占; 
	// deviceDriver  设备驱动;    // 配置设备模版; 
	
	
	// 获取 driver 列表; 
	@Autowired
	public  ThingLinxRest  rest ; 
	
	
	@RequestMapping( value="/dtu" , method=RequestMethod.GET )
	public Object getDTUDrivers ( 
			@ModelAttribute(Global.session_key_user) User user  
			){
		
		return  rest.https.get(user, "/query/drivers", null, 
						SuffixParams.get().put("category", "CHANNEL")
						);
		 
//		return rest.http.get( "/query/drivers", null, 
//				SuffixParams.get().put("category", "CHANNEL")
//				);
		
	}
	
	 
	
	@RequestMapping( value="/device" , method=RequestMethod.GET )
	public Object getDeviceDrivers ( 
			@ModelAttribute(Global.session_key_user) User user  
			){
		
		return rest.https.get(user, "/query/drivers", null,
				SuffixParams.get().put("category", "DEVICE")
				);
		
		
//		return rest.http.get( "/query/drivers", null,
//				SuffixParams.get().put("category", "DEVICE")
//				);
		
		
		
 
		
//		HashMap dtuDriver1 = new HashMap();
//		dtuDriver1.put("driver_id", "Dtu_HongDian");
//		dtuDriver1.put("version", "1.0.0.0");
//		dtuDriver1.put("cmway", "211.100.14.158");
//		dtuDriver1.put("port", 5223);
//		 
//		 
//		HashMap   deviceDriver1 =  new HashMap (); 
//		deviceDriver1.put("id", "2");
//		deviceDriver1.put("driver_id", "FCS_MODBUS"); 
//		
//		HashMap   deviceDriver2 =  new HashMap (); 
//		deviceDriver2.put("id", "3");
//		deviceDriver2.put("driver_id", "PLC_SIEMENS_PPI"); 
//		
//		 
//		ArrayList   driverList =  new ArrayList();
//		
//		if( drivertype.equalsIgnoreCase("device")){
//			driverList.add(deviceDriver1); 
//			driverList.add(deviceDriver2);
//			
//		}
//		if(drivertype.equalsIgnoreCase("dtu")){
//			driverList.add(dtuDriver1);
//			
//		}
//		return  RESP(driverList) ; 
		
		 
	}
	 

}
