package com.sunwayland.rest.url;

public class SystemUrl  {
	
	 
			public static final String create = "/systems";
			public static final String delete = "/systems/{system_id}";
			public static final String select = "/systems/{system_id}";
			public static final String update = "/systems/{system_id}";
			
			
			public static final String query  = "/query/systems";
			
			public static final String call   ="/systems/{system_id}/control/poll" ;
			public static final String start  ="/systems/{system_id}/control/start" ;
			public static final String stop   ="/systems/{system_id}/control/stop" ;
			public static final String sync   ="/systems/{system_id}/sync" ;
			
			 
			//点 下置数据; 
			public static final String dumpdata = "/systems/{system_id}/live/write";
					
			
			
			public static String  selectContact   = "/systems/{system_id}/contact";
			public static String  updateContact   = "/systems/{system_id}/contact";
			
			public static String  createContact   = "/contacts";
			
			
			
			
	 // http ; 
	 public static String needSync        = "/info/needupdate/systems";
		
	 // https ; 
	 public static String  live    = "/systems/{system_id}/live";
	 
	 public static String  history        = "/systems/{system_id}/log/readinterval";
			
	 
	 public static String  alarmhistory   = "/systems/{system_id}/alarmhistory";
	 public static String  alarmcurrent   = "/systems/{system_id}/alarms";

			

			
		  
	
}
