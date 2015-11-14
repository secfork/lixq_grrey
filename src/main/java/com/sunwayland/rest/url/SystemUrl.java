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
			
			public static final String queryStatus = "/query/systems/status";
			 
			//点 下置数据; 
			public static final String dumpdata = "/systems/{system_id}/control/write";
					
			
			
			
			public static String  selectContact   = "/systems/{system_id}/contact";
			public static String  updateContact   = "/systems/{system_id}/contact";
			
			public static String  createContact   = "/contacts";
			
			
			
			
	 // http ; 
	 public static String needSync        = "/info/needupdate/systems";
		
	 // https ; 
	 public static String  live    = "/systems/{system_id}/live";
	 
	 /**
	  * 历史趋势查询;
	  */
	 public static String  historyInterval        = "/systems/{system_id}/log/readinterval";
	 public static String  historyReadRow        = "/systems/{system_id}/log/readraw";
			
	 
	 // 活跃报警
	 public static String  alarmcurrent   = "/systems/{system_id}/alarms";
	 // 全部报警;
	 public static String  alarmhistory   = "/systems/{system_id}/alarmhistory";

/**
	 /query/alarms?accesskey={accesskey}?active=[ -1 || 0 ||1 ]
	 start={number}[&end={number}][&timestamp={timestamp}[&timestamp=...]][&close_time={close_time}][&close_time=...][&region_id={region_id}]
	 [&type={number}[&type=...]][&info={*string*}][&severity= {number}[&severity=...]][&ack_id={ack_id}]
	 [&class_id={number}[&class_id=...]][&sorts={field[-]}[&sorts=...]][&offset={start_item_index}]
	 [&limit={max_count}][&calc_sum={true|false}]
*/
	public static String  queryAlarm = "/query/alarms";	
		  
	
}
