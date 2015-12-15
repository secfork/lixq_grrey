package com.sunwayland.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.utils.Utils;
import com.sunwayland.core.vo.WebPage;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.basic.FieldEnum;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.GenericParams;
import com.sunwayland.rest.params.Params;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.SystemUrl;
import com.sunwayland.rest.vo.LiveWriteData;
import com.sunwayland.web.vo.Global;

@Controller
@RequestMapping("show")
@ResponseBody
@SuppressWarnings("unchecked")
//@SessionAttributes("user")
public class ShowController extends GenericAction {
 
	
	Logger log = Logger.getLogger(ShowController.class);
	
	@Autowired
	public  ThingLinxRest  rest ; 
	
	
	/**
	 * 
	 * @param user
	 * @param uuid
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/live/{uuid}", method = RequestMethod.GET )
	public Object LiveOneTagData(@ModelAttribute(Global.session_key_user) User user, 
			           @PathVariable String uuid, String[] tag) {
		if (null == tag)
			return null;

		Params params = SuffixParams.get();

		for (String t : tag) {
			params.tag(t);
		}

		return rest.System.get(user, SystemUrl.live, UrlParams.get().system_id(uuid),
				params);

	}

	
	/**
	 *  
	 */
	@RequestMapping(value = "/history/{system_uuid}", method = RequestMethod.GET)
	public Object tagHistoryData( 
			@ModelAttribute( Global.session_key_user)  User user ,
			@PathVariable String system_uuid,
			String[] tag ,
			Long start,
			Long end, 
			Integer num ,  // 数据返回条数;
			String type
			) {

		if (0 == tag.length )
			return null;
		Params params = SuffixParams.get() ;
		
		SuffixParams params2 = SuffixParams.get();
		
		params.getMap().put("start", start);
		params.getMap().put("end", end);
		//采样的条数，即每一个TAGID在[start, end]区间内以等时间间隔的形式采样的记录数，最大为1000 ;
		params.getMap().put("count", num >400?400:num );
	 
		
		
		
		
		
		params2.getMap().put("t", start);
		//  analog : linear ;,
		//  digital : last_value ;
		
		params2.getMap().put("mode", "Analog"==type ?"linear":"last_value");
		 
		for (String t : tag) {
			params.tag(t);
			params2.tag(t);
		}
		
		//判断起始时间和结束时间，当两者差距小于24小时，改用查询原始值接口
		String  url = (end - start) > 1000*60*60*6? 
			SystemUrl.historyInterval:
			SystemUrl.historyReadRow;
		 
		//tag attime date ; 
		// /json/systems/{system_id}/log/readattime?accesskey={accesskey}
//		&tag=tag1[&tag=tag2][&tag=...] 

		
		
		Map map = rest.https.get(user, "/systems/{system_id}/log/readattime" , 
				
						UrlParams.get().system_id(system_uuid) ,
						 params2);
		
		
	    Map map2 = rest.System.get( user ,url, 
	    		UrlParams.get().system_id(system_uuid),
	    		
	    		params);

	    return  new Object[]{ map , map2 };
	}	
	

	
	
	
	
	/**
	 * 查询活跃报警 (/alarms 接口; );  即 未确认的报警; 
	 * @param user
	 * @param system_uuid
	 * @return
	 */
	@RequestMapping(value = "/alarm/{system_uuid}",  params= {"currentPage"} ,
			method = RequestMethod.GET)
	public Object queryActiveAlarm(
			@ModelAttribute( Global.session_key_user)  User user ,
			@PathVariable String system_uuid ,
			Long start,
			Long end,
			String severity,
			String  class_id ,
			WebPage  webpage  
			) {

		SuffixParams total = SuffixParams.get() ; 
		total.put("start", start)
			.put("end", end)
		    .put("severity", severity)
		    .put("class_id", class_id)
		    .calc_sum(true);
		
		
		SuffixParams data = SuffixParams.get(); 
		  data.put("start", start)
		    .put("end", end)
	        .put("severity", severity) 
		    .put("class_id", class_id)
		    .offset(webpage.getOffset())
		    .limit(webpage.getLimit()) 
		   // .sorts(FieldEnum.create_time, true);
//		    timestamp
		    .put("sorts", "id-");
		  
		return  rest.https.query(user, SystemUrl.alarmcurrent,
				  UrlParams.get().system_id(system_uuid),
				  total, data);
  
		
	}
	
//	CKNOWLEDGE ALARM / AND CLOSE IT(确认并关闭指定的报警)
//	put    /systems/{system_id}/alarm/ack?accesskey={accesskey}
//  				&id={number}[&user_id={number}][&message={string}]

//	GET ACK MESSAGE(获得确认报警的处置消息)
//	GET     /systems/{system_id}/alarm/ack?accesskey={accesskey}&id={number}
	
	@RequestMapping( value="/alarm/confirm" , params={"system_id","alarm_id"} , method = RequestMethod.POST )
	public Object conformAlarm (
			@ModelAttribute( Global.session_key_user)  User user ,
			String system_id ,
			String alarm_id ,
			String message 
			){
		  
		HashMap body = new HashMap();
		body.put("message", Utils.iso2utf(message));
		body.put("user_id", user.getId());
		 
		
					// wiki 上 错误 ,  本就是 put ; 
	   return   rest.https.put(user, "/systems/{system_id}/alarm/ack", body,
			   		UrlParams.get().system_id(system_id),
	    		 	SuffixParams.get().id(alarm_id)  );
		 
	}
	
	@RequestMapping( value="/alarm/confirm" , params={"system_id","ack_id"} , method = RequestMethod.GET )
	public Object getConformMsgOfAlarm (
			@ModelAttribute( Global.session_key_user)  User user ,
			String system_id ,
			String ack_id 
			){
		 
		
	   return  rest.https.get(user, "/systems/{system_id}/alarm/ack", 
			   		 UrlParams.get().system_id(system_id),
	    		 	SuffixParams.get().id(ack_id) );
		 
	}
	
	

	/**
	 * 查询 全部  报警 ( /alarmhistory 接口; );   即(非活跃) 已经确认了的报警  + ( 活跃) 未确认的报警;  
	 * @param user
	 * @param system_uuid
	 * @param header
	 * @param map
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/alarm/{system_uuid}",  params= {"currentPage"} , 
			method = RequestMethod.POST)
	public Object queryAllAlarm( 
			@ModelAttribute( Global.session_key_user)  User user ,
			@PathVariable String system_uuid, 
			HttpServletRequest request , 
			Long start,
			Long end,
			String severity,
			String  class_id ,
			WebPage webpage
			) {
		
		 
		
		Params total = SuffixParams.get() ;
		total.put("start", start)
		.put("end", end)
		.put("severity", severity)
		.put("class_id", class_id)
		.calc_sum(true);

		Params data = SuffixParams.get() ; 
		data.put("start", start)
		.put("end", end)
		.put("severity", severity)
		.put("class_id", class_id)
		.limit(webpage.getLimit())
		.offset(webpage.getOffset())
		 .put("sorts", "id-");
		 

		
		
	  return rest.https.query(user, SystemUrl.alarmhistory, 
				UrlParams.get().system_id(system_uuid),
				total, data
				);  
	}

	
	
	@RequestMapping( value="/alarm/query" , method= RequestMethod.GET)
	public Object queryAlarm (  
			@ModelAttribute( Global.session_key_user)  User user ,
			HttpServletRequest  req ,
			@RequestParam Map params
			  
			){ 
		
		 
		
		SuffixParams total  = SuffixParams.get();
		SuffixParams data  = SuffixParams.get();
		
		
		total.getMap().putAll(params);
		total.calc_sum(true);
		
		data.getMap().putAll(params);
		data.getMap().put("sorts", "id-");
		
		Map query = rest.https.query(user, SystemUrl.queryAlarm, null , total , data);
		
		return query ; 
	}
	
	
	
	/*
	 * 下置数据; 
	 */
	
	@RequestMapping(value = "/livewrite/{uuid}", method = RequestMethod.POST)
	public Object  writeData (
							@ModelAttribute( Global.session_key_user)  User user ,
							@RequestBody String  data ,
							@PathVariable String uuid 
							){	
			  
		
		return  rest.https.post(user, SystemUrl.dumpdata,  data,
					UrlParams.get().system_id(uuid),
					SuffixParams.get());
	
	 
	}
	
	
	
	
	
	
	
}
