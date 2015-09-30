package com.sunwayland.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.utils.Utils;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.eneityV2.User;
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

	
	@RequestMapping(value = "/history/{system_uuid}", method = RequestMethod.GET)
	public Object tagHistoryData( 
			@ModelAttribute( Global.session_key_user)  User user ,
			@PathVariable String system_uuid,
			String[] tag ,
			String start, String end, Integer num
			) {

		if (null == tag)
			return null;
		Params params = SuffixParams.get() ;
		params.getMap().put("start", start);
		params.getMap().put("end", end);
		params.getMap().put("count", num);
		params.limit(num);

		for (String t : tag) {
			params.tag(t);
		}
		return rest.System.get( user , SystemUrl.history, UrlParams.get().system_id(system_uuid), params);

	}

	/**
	 * 查询活跃报警 (/alarms 接口; );  即 未确认的报警; 
	 * @param user
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/alarm/{uuid}", method = RequestMethod.GET)
	public Object liveCurrentAlarm(
			@ModelAttribute( Global.session_key_user)  User user ,
			@PathVariable String uuid ,
			Long start,
			Long end,
			String severity,
			String  class_id 
			
			) {

		Params params = SuffixParams.get() ;
		params.getMap().put("start", start);
		params.getMap().put("end", end);
		params.getMap().put("severity", severity);
		params.getMap().put("class_id", class_id);
		
		return rest.System.get( user , 
				SystemUrl.alarmcurrent, 
				UrlParams.get().system_id(uuid),
				params 
				); 
	}

	/**
	 * 查询 全部  报警 ( /alarmhistory 接口; );   即(非活跃) 已经确认了的报警  + ( 活跃) 未确认的报警;  
	 * @param user
	 * @param uuid
	 * @param header
	 * @param map
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/alarm/{uuid}", method = RequestMethod.POST)
	public Object queryAllAlarm( 
			@ModelAttribute( Global.session_key_user)  User user ,
			@PathVariable String uuid, 
			HttpServletRequest request , 
			Long start,
			Long end,
			String severity,
			String  class_id 
			) {
		
		 
		
		Params params = SuffixParams.get() ;
		params.getMap().put("start", start);
		params.getMap().put("end", end);
		params.getMap().put("severity", severity);
		params.getMap().put("class_id", class_id);

		return rest.System.get( user , 
				SystemUrl.alarmhistory,
				UrlParams.get().system_id(uuid), 
				params);

	}

	/*
	 * 下置数据; 
	 */
	
	@RequestMapping(value = "/livewrite", method = RequestMethod.POST)
	public Object  writeData (
							@ModelAttribute( Global.session_key_user)  User user ,
							@Validated @RequestBody LiveWriteData  data ,
							BindingResult result
							){	
			
		Utils.handlerBindngResult(result);
		 
		
		return rest.System.liveWrite( user , data.getSystem_id() , data.getTagname() , data.getValue());
	
	}
	
	
	
	
	
	
	
}
