package com.sunwayland.rest;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.expr.NewArray;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;

public class RestUtils {

	static Logger log = Logger.getLogger(RestUtils.class);
	private static Gson gson = new Gson();

	public static String parseMapUrlSuffixParams(MultiHashMap urlParams) {

		if (CollectionUtils.isEmpty(urlParams)) {
			return "";
		}

		Set<String> keySet = urlParams.keySet();

		StringBuffer buffer = new StringBuffer();

		for (String key : keySet) {
			Object object = urlParams.get(key);

			if (object instanceof List) {
				for (Object o : (List) object) {
					if (null == o)
						continue;
					try {
						buffer.append(key + "=" + new String(o.toString().getBytes("iso-8859-1"), "utf-8") + "&");
					} catch (Exception e) {
					}
				}

			} else {
				try {
					buffer.append(key + "=" + new String(object.toString().getBytes("iso-8859-1"), "utf-8") + "&");
				} catch (UnsupportedEncodingException e) {
				}

			}

		}

		String url = buffer.toString();
		url = "?" + StringUtils.substringBeforeLast(url, "&");

		return url;

	}

	/*
	 * 解决 body 中文;
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HttpEntity createJosnBody(Object body) {
		
		if (null == body)
			return null; 
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON); 

		String json;

		if (body instanceof String) {
			json = (String) body;
		} else {
			json = gson.toJson(body);
		}

		try {

			// json = new String(json.getBytes("iso-8859-1"), "utf-8");
			json = new String(json.getBytes("utf-8"), "iso-8859-1");

		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		log.info("  body "+ json );
		
		HttpEntity requestbody = new HttpEntity(json, headers);
		return requestbody;

	}
	
	/**
	 * apllyaccesskey 专用; 
	 * @param body
	 * @param remoteIp
	 * @return
	 * 
	 */
	
	public static HttpEntity createHttpRequestEntity ( Object body , String remoteIp ){ 
		if (null == body)
			return null; 
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON); 
		
		headers.set("thinglinx-remote-address", remoteIp);

		String json;

		if (body instanceof String) {
			json = (String) body;
		} else {
			json = gson.toJson(body);
		}

		try {

			// json = new String(json.getBytes("iso-8859-1"), "utf-8");
			json = new String(json.getBytes("utf-8"), "iso-8859-1");

		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpEntity requestbody = new HttpEntity(json, headers);
		return requestbody;
		
		
		
		
		
	}
	

	/**
	 * rest 响应 处理;
	 * 
	 * @param response
	 */
	public static void handlerResponse(Map response) {
		String err = (String) response.get("err");

		if ("ER_NO_AUTH_OR_EXPIRED".equalsIgnoreCase(err)) {
			// throw new UnauthenticatedException();
		}

	}

}
