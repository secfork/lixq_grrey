package com.sunwayland.rest;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;

public class RestUtils {

	private static Gson gson = new Gson();

	public static String parseMapUrlSuffixParams(MultiHashMap urlParams) {

		if (CollectionUtils.isEmpty(urlParams))
			return "";

		Set<String> keySet = urlParams.keySet();

		StringBuffer buffer = new StringBuffer();

		for (String key : keySet) {
			List list = (List) urlParams.get(key);
			if (null == list)
				continue;
			for (Object o : list) {
				if( null == o ){
					continue ;
				}
				buffer.append(key + "=" + o.toString() + "&");
			}
		}
		String url = buffer.toString();

		url = "?" + StringUtils.substringBeforeLast(url, "&");

		return url;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HttpEntity createJosnBody(Object body) {
		if (null == body)
			return null;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// setContentType(MediaType.APPLICATION_JSON) 源码 ==
		// headers.add("Content-Type", "application/json");

		String json ;
		
		if( body instanceof String ){
			 json  =  (String) body ; 
		}else{
			 json =  gson.toJson(body) ; 
		}
		
		try {
			// json = new String(json.getBytes("utf-8"),
			// this.getServerDecode());
			json = new String(json.getBytes("utf-8"), "iso-8859-1");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		
		HttpEntity requestbody = new HttpEntity(json, headers);
		return requestbody;

	}

	/**
	 * rest 响应 处理; 
	 * @param response
	 */
	public static void   handlerResponse ( Map response ){
		 String err = (String) response.get("err");
		 
		 if("ER_NO_AUTH_OR_EXPIRED".equalsIgnoreCase(err)){
			// throw new UnauthenticatedException();
		 }
		 
		
	}
	
	
}
