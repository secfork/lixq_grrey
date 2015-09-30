package com.sunwayland.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sunwayland.core.generic.GenericAction;
import com.sunwayland.core.utils.Utils;
import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.core.vo.WebPage;
import com.sunwayland.rest.ThingLinxRest;
import com.sunwayland.rest.basic.CallType;
import com.sunwayland.rest.eneityV2.Contact;
import com.sunwayland.rest.eneityV2.System;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.GenericParams;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.OrtherUrl;
import com.sunwayland.rest.url.SystemUrl;
import com.sunwayland.web.vo.Global;

@Controller
@ResponseBody
@RequestMapping("system")
//@SessionAttributes("user")
public class SystemController extends GenericAction {
 

 
	// =============================== system    =============================
	
	@RequestMapping(method = RequestMethod.GET , params={"isactive"}  )
	public Object querySystem(@ModelAttribute(Global.session_key_user) User user,
			String isactive,
			String f_dasname ,
			WebPage page
			) {
		 
		
		
		GenericParams suffix_data  = SuffixParams.get().limit(page.getLimit()).offset(page.getOffset());
		 
	    GenericParams suffix_total = SuffixParams.get().calc_sum(true);
		
		if( StringUtils.isNotBlank(f_dasname)){
			f_dasname +=  f_dasname.endsWith("*")?"":"*";	 
			suffix_data.put("name", f_dasname);
			suffix_total.put("name", f_dasname);
		} 
		
		if ("0".equals(isactive)) {
			return rest.System.query(user,
					SystemUrl.query,
					UrlParams.get(),
					suffix_total.state("0").state("2") ,  
					suffix_data.state("0").state("2")
					);
		}

		if ("1".equals(isactive)) {
			return rest.System.query(user
					, SystemUrl.query,
					UrlParams.get(),
					suffix_total.state("1") ,  
					suffix_data.state("1") 
					);
		}

		return RESP_ERR(" field isaceive must be 0 or 1");
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object createSystem(@ModelAttribute(Global.session_key_user) User user,
//			@Validated(Create.class) @RequestBody System system, 
//			BindingResult result ,
			@RequestBody  String system 
			
			) {
//		Utils.handlerBindngResult(result);

		 
		Map map = rest.System.createSystem(user, system);

		Object system_id = map.get(Global.ret_key);
		if (null != system_id) {
			// 创建历史空间;
			rest.http.post(   OrtherUrl.historySpabe, UrlParams.get(),
						SuffixParams.get().system_id(system_id.toString()), 
						SuffixParams.get()
					 );

		}
		// thinglinxStationService.updateSN( sn.getId() , true );

		return map;

	}

	
	@RequestMapping(method = RequestMethod.DELETE , params={"system_id"})
	public Object deleteSystem(@ModelAttribute(Global.session_key_user) User user, 
			String system_id) {
		
		return rest.System.deleteSystem(user , system_id);
	}

	/**
	 * 获取 system  包括  device , profile , tag ,message , trigger ; 
	 * @param user
	 * @param system_id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET , params={"system_id"})
	public Object getSystemIncludeInfo(@ModelAttribute(Global.session_key_user) User user, 
			String system_id) {
		
		return  rest.System.getSystem(user, system_id, true, true , true);
		
		 
	}

	
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public Object updateSystem(@ModelAttribute(Global.session_key_user) User user,
			@Validated(Update.class) @RequestBody System system,
			BindingResult result) {
		Utils.handlerBindngResult(result);

		return rest.System.updateSystem(user ,  system.getUuid(), system);

	}

	// ============proj 下的 system 分页显示;   /of_proj/{prject_id} ===========

	
	@RequestMapping(value = "/of_proj/{project_id}")
	public Object getSystemOfRegion(@ModelAttribute(Global.session_key_user) User user,
			@PathVariable String project_id,
			WebPage page) {

		return rest.System.query(user , 
				SystemUrl.query,
				UrlParams.get(),
				SuffixParams.get().region_id(project_id).calc_sum(true),
				SuffixParams.get().limit(page.getLimit())
						.offset(page.getOffset())
						.region_id(project_id)
				);

	}

	 //===============  /needsync/uuids  ====================
	
	@RequestMapping(value = "/needsync/uuids", method = RequestMethod.GET)
	public Object getSystemNeedToSync(@ModelAttribute(Global.session_key_user) User user, 
			        String[] uuids) {

		SuffixParams suffixParams = SuffixParams.get();
		if (null ==uuids || uuids.length == 0)
			return null;

		for (String id : uuids) {
			suffixParams.id(id);
		}

		return rest.http.get(  SystemUrl.needSync, UrlParams.get(), suffixParams);

	}

	// ==================== systerm 远程控制; /{systemuuid} ========================

	
	@RequestMapping(value = "/{systemuuid}", method = RequestMethod.GET)
	public Object startSys(@ModelAttribute(Global.session_key_user) User user, @PathVariable String systemuuid) {
		return rest.System.startSystem(user ,  systemuuid);
	}

	
	@RequestMapping(value = "/{systemuuid}", method = RequestMethod.DELETE)
	public Object stopSys(@ModelAttribute(Global.session_key_user) User user, @PathVariable String systemuuid) {
		return rest.System.stopSystem(user ,  systemuuid);
	}

	// 召唤实时; 
	@RequestMapping(value = "/{systemuuid}", method = RequestMethod.POST  , params= {"type"})
	public Object callSys(
			@ModelAttribute(Global.session_key_user) User user,
			@PathVariable String systemuuid ,
			int type 
			) {
		
		if( 0 == type ){
			return rest.System.callSystem(user ,  systemuuid , CallType.CALL_REALTIME);			
		}
		if( 1 == type ){
			return rest.System.callSystem(user ,  systemuuid , CallType.CALL_STANDBY);			
			
		}
		if( 2 == type ){
			return rest.System.callSystem(user ,  systemuuid , CallType.CALL_ALL);			
			
		}
		
		return null ; 
		
		
	}

	
	@RequestMapping(value = "/{systemuuid}", method = RequestMethod.PUT)
	public Object synctSys(@ModelAttribute(Global.session_key_user) User user, @PathVariable String systemuuid) {
		return rest.System.syncSystem(user ,  systemuuid);
	}

	// =====================system 下的 contact 联系人   /contacts/{system_uuid} =====================================================
	
	@RequestMapping(value = "/contacts/{system_uuid}", method = RequestMethod.GET)
	public Object selectSystemContact(
			@ModelAttribute(Global.session_key_user) User user,
			@PathVariable String system_uuid
			) {

		if (null == system_uuid)
			return null;

		return rest.System.get( user , 
				SystemUrl.selectContact, 
				UrlParams.get().system_id(system_uuid),
				SuffixParams.get() 
				);

	}

	// /thinglinx/web/system/contacts/system/contacts/VkmjoWeYy3
	
	@RequestMapping(value = "/contacts/{system_uuid}", method = RequestMethod.POST)
	public Object createSystemContact(@ModelAttribute(Global.session_key_user) User user,
			@PathVariable String system_uuid,
			@Validated(Create.class) @RequestBody Contact contact,
			HttpSession session ,
			BindingResult result

			) {

		Utils.handlerBindngResult(result);
		
		
		// 验证 短信 是否失效; 
		String code = contact.getCode();
		
		long  timeout = (long) session.getAttribute(Global.session_connect_timeout);
		String   _code  = (String) session.getAttribute(Global.session_key_connect_verifiy);
		
		if( !_code.equalsIgnoreCase(code) ||  timeout < new Date().getTime() ){
			return  RESP_ERR("短信验证码无效!");
		}
		
		contact.setCode(null);
		
		return rest.System.post(user ,
				SystemUrl.createContact,
				contact,
				UrlParams.get().system_id(system_uuid),
				SuffixParams.get() 
				);

	}

	
	@RequestMapping(value = "/contacts/{system_uuid}", method = RequestMethod.PUT)
	public Object updateSystemContact(@ModelAttribute(Global.session_key_user) User user,
			@PathVariable String system_uuid,
			@Validated(Update.class) @RequestBody Contact contact,
			BindingResult result

			) {
		if (null == system_uuid)
			return null;
		Utils.handlerBindngResult(result);

		return rest.System.put( user, 
				SystemUrl.updateContact, 
				contact,
				UrlParams.get().system_id(system_uuid),
				SuffixParams.get() 
				);

	}
	
	//=================================    /assign     分配 dtu systen 的  daserver ; 
	
	//   dtu 类型 system 设置 driver 时 ;
	@RequestMapping( value="/assign/{system_uuid}"  , params={"driver_id"} ,  method = RequestMethod.GET )
	public  Object assignDTUSystem ( 
			@ModelAttribute(Global.session_key_user) User user ,
			@PathVariable String  system_uuid ,
			String  driver_id 
			){
		
		HashMap body = new HashMap();
		body.put("driver_id", driver_id);
		
	  return 	rest.http.put("/info/systems/{system_id}/assign", body, 
			   UrlParams.get().system_id(system_uuid),  null );
		 
	}
	
	//   dtu 类型 system 设置 driver 时 ;
	@RequestMapping( value="/getassign/{system_uuid}"  ,   method = RequestMethod.GET )
	public  Object getDTUServerOfSystem ( 
			@ModelAttribute(Global.session_key_user) User user ,
			@PathVariable String  system_uuid 
			){
		  
	  return 	rest.http.get("/info/systems/{system_id}/assign",
			  UrlParams.get().system_id(system_uuid),  null );
		 
	}
	
	
	

}
