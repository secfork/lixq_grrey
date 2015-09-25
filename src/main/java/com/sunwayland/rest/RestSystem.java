package com.sunwayland.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.sunwayland.rest.basic.CallType;
import com.sunwayland.rest.basic.HttpsRest;
import com.sunwayland.rest.eneityV2.System;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.GenericParams;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.SystemUrl;

/**
 * @author Administrator
 *
 * @param <T>
 */
@SuppressWarnings({ "unused", "rawtypes" })
public class RestSystem extends HttpsRest {

	public RestSystem(RestTemplate restTemplate, String base_url) {
		super(restTemplate, base_url);
	}

	// =================================================================

	public Map createSystem(User user, Object system) {

		return this.post(   user ,SystemUrl.create, system,
							UrlParams.get(), 
							SuffixParams.get()
							);

	}

	public Map getSystem( User user, String system_id, 
									 boolean extend_profile,
									 boolean extend_device , 
									 boolean  include_tags
						) {
		return this.get( user, 
						 SystemUrl.select,
						 UrlParams.get().system_id(system_id),
						 SuffixParams.get().extend_device(extend_device)
						 				   .extend_profile(extend_profile)
						 				   .put("include_tags", include_tags)
						);

	}

	public Map updateSystem( User user , String systemId, System system) {

		return this.put( user , 
				SystemUrl.update,
				system,
				UrlParams.get().system_id(systemId),
				SuffixParams.get());

	}

	public Map deleteSystem(User user, String system_id) {
		if (null == system_id)
			return null;
		return this.delete( user , SystemUrl.delete,
				null,
				UrlParams.get().system_id(system_id),
				SuffixParams.get()
				);

	}

	public Map querySystem(User user, GenericParams QueryParmas) {
		 
		return this.get( user , SystemUrl.query, UrlParams.get() , QueryParmas);
	}

	/*
	 * 下置点数据; 
	 */
	public Map liveWrite(User user , String system_id , String tag_name , Object value ){
		
		HashMap<String, String> body = new HashMap<String, String>();
		body.put("name", tag_name);
		body.put("value", value.toString() );
		
		Object[]  p ={body};
		return this.post(user, SystemUrl.dumpdata, p,
						UrlParams.get().system_id(system_id),
						SuffixParams.get());
		
		 
	}
	
	
	
	/**
	 * 下置!!
	 * 
	 * @return
	 */
	public Object syncSystem( User user, String system_uuid) {
		return this.put( user , 
				SystemUrl.sync, 
				null,
				UrlParams.get().system_id(system_uuid),
				SuffixParams.get()
				);
	}

	/**
	 * 启动!!
	 * 
	 * @return
	 */
	public Object startSystem( User user , String system_uuid) {
		return this.put( user , SystemUrl.start,
				null,
				UrlParams.get().system_id(system_uuid),
				SuffixParams.get()
				);
	}

	/**
	 * 召唤!!
	 * stype 传 null 时 默认 "CALL_REALTIME" : 
	 * 
	 * {
		 "type" : "CALL_REALTIME"(default) || "CALL_STANDBY" || "CALL_ALL" || "CALL_LOG"
		}
	 * @return
	 */ 
	public Map callSystem(User user, String system_uuid , CallType type ) {
		
		HashMap body = new HashMap();
		
		body.put("type",  type );
		
		return this.post(user, SystemUrl.call, body, UrlParams.get().system_id(system_uuid),
				SuffixParams.get()); 
		 
	}

	/**
	 * 停止!!!
	 * 
	 * @return
	 */
	public Object stopSystem(User user, String system_uuid) {
		return this.put( user , SystemUrl.stop,
				null,
				UrlParams.get().system_id(system_uuid),
				SuffixParams.get());
	}

}
