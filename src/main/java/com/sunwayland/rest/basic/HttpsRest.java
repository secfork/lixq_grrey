package com.sunwayland.rest.basic;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sunwayland.core.exception.ExceptionMessage;
import com.sunwayland.rest.RestUtils;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.GenericParams;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.OrtherUrl;
import com.sunwayland.web.vo.Global;

public  class HttpsRest {

	public Gson gson = new GsonBuilder().serializeNulls().create();

	Logger logger = Logger.getLogger(HttpsRest.class);
	// new Gson() ;

	public RestTemplate httpsRest;
	public String https_http_Url;

	private Object object;
	 
	public HttpsRest() {
		super();
	}

	public HttpsRest(RestTemplate restTemplate, String base_url) {
		this.httpsRest = restTemplate;
		this.https_http_Url = base_url;
	}

	/**
	 * 测试 ;
	 * 
	 * @return
	 */
	public Map test(String url) {
		Map string = httpsRest.getForObject(url, Map.class);
		return string;
	}

	/**
	 * 
	 * @param url
	 *            必须;
	 * @param suffixParams
	 *            根据实际情况 可传 null ;
	 * @param urlParams
	 *            根据实际情况 可传 null ; 只填补 url中的站位符 {?} ;
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map get( User user ,  String url, GenericParams urlParams, GenericParams suffixParams) {
		
		validAccesskey(user); 
		suffixParams =  null==suffixParams?SuffixParams.get():suffixParams;
		urlParams = null == urlParams?UrlParams.get():urlParams;
		 
		suffixParams.getMap().put( VarFieldEnum.accesskey.toString(), user.getAccessKey() );
		
		
		Map<String, String> pathPar =   urlParams.getMap(); 
		MultiHashMap urlPar =   (MultiHashMap) suffixParams.getMap();

		url = https_http_Url + url;

		url += RestUtils.parseMapUrlSuffixParams(urlPar);

		if (CollectionUtils.isEmpty(pathPar)) {
			return httpsRest.getForObject(url, Map.class);
		} else {
			return httpsRest.getForObject(url, Map.class, pathPar);

		}

	};

	/**
	 * 
	 * @param url
	 * @param body
	 *            根据实际情况 可传 null ;
	 * @param suffixParams
	 *            根据实际情况 可传 null ;
	 * @param urlParams
	 *            根据实际情况 可传 null ; 只填补 url中的站位符 {?} ; ;
	 * @return
	 */
	public Map put( User user , String url, Object body, GenericParams urlParams, 	
			GenericParams suffixParams) {
		
		validAccesskey(user); 
		suffixParams =  null==suffixParams?SuffixParams.get():suffixParams;
		urlParams = null == urlParams?UrlParams.get():urlParams;
		
		
		suffixParams.getMap().put( VarFieldEnum.accesskey.toString(), user.getAccessKey() );
		
		

		Map<String, String> pathPar =  urlParams.getMap();

		MultiHashMap urlPar = (MultiHashMap) suffixParams.getMap();

		url = https_http_Url + url;

		if (!CollectionUtils.isEmpty(urlPar)) {
			url += RestUtils.parseMapUrlSuffixParams(urlPar);
		}

		ResponseEntity<Map> exchange;

		if (CollectionUtils.isEmpty(pathPar)) {
			exchange = httpsRest.exchange(url, HttpMethod.PUT,
					RestUtils.createJosnBody(body), Map.class);

		} else {
			exchange = httpsRest.exchange(url, HttpMethod.PUT,
					RestUtils.createJosnBody(body), Map.class, pathPar);

		}
		return exchange.getBody();
	}

	/**
	 * 
	 * @param url
	 * @param body
	 *            根据实际情况 可传 null ; 
	 * @return
	 */
	public Map post(    User user , 
						String url,
						Object body, 
						GenericParams urlParams,
						GenericParams suffixParams
				   ) {
		
		
		validAccesskey(user); 
		
		suffixParams =  null==suffixParams?SuffixParams.get():suffixParams;
		urlParams = null == urlParams?UrlParams.get():urlParams;
		
		 
		
		suffixParams.getMap().put( VarFieldEnum.accesskey.toString(), user.getAccessKey() );
	

		Map<String, String> pathPar = urlParams .getMap();

		MultiHashMap urlPar =   (MultiHashMap) suffixParams.getMap();

		url = https_http_Url + url;

		if (!CollectionUtils.isEmpty(urlPar)) {
			url += RestUtils.parseMapUrlSuffixParams(urlPar);
		}

		if (CollectionUtils.isEmpty(pathPar)) {
			Map map = httpsRest.postForObject(url,
					RestUtils.createJosnBody(body), Map.class);
			return map;
		} else {
			Map map = httpsRest.postForObject(url,
					RestUtils.createJosnBody(body), Map.class, pathPar);
			return map;
		}
	}

	/**
	 *  //默认按 createtiem  倒叙排列; 
	 *  create_time 排序; query 分页数据; 返回: { total : xx , data:[{},{}, ... ] } 数据
	 * ;
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map query( User user ,
						String restQueryUrl,
						GenericParams urlParams, 
						GenericParams suffixParams_total,
						GenericParams suffixParams_data) {
		  
		Map re = new HashMap();

		urlParams = null == urlParams?UrlParams.get():urlParams;
		suffixParams_total =  null==suffixParams_total?SuffixParams.get():suffixParams_total;
		suffixParams_data =  null==suffixParams_data?SuffixParams.get():suffixParams_data;
		
		
		
		 // 默认 ; .sorts(FieldEnum.create_time, false) ;  
//		 Object object2 = suffixParams_data.getParams().get(FieldEnum.create_time.toString()); 
//		 if( null == object2){
//			 suffixParams_data.sorts(FieldEnum.create_time, false);	
//		 }
		

		Map total = this.get( user ,  restQueryUrl, urlParams, suffixParams_total );

		Map data = this.get( user ,   restQueryUrl, urlParams, 	suffixParams_data);
		
		  
		
		re.put("data", data.get(Global.ret_key));
		re.put("total", total.get(Global.ret_key));  
		 
		return re;

	}

	/**
	 * 
	 * @param url
	 * @param body
	 *            根据实际情况 可传 null ;
	 * @param suffixparams
	 *            根据实际情况 可传 null ;
	 * @param urlparams
	 *            根据实际情况 可传 null ; 只填补 url中的站位符 {?} ; ;
	 * @return
	 */
	public Map delete(  User user ,
						String url,
						Object body, 
						GenericParams urlparams, 
						GenericParams suffixparams) {

		validAccesskey(user); 
	 
		suffixparams =  null==suffixparams?SuffixParams.get():suffixparams;
		urlparams = null == urlparams?UrlParams.get():urlparams;
		
		
		
		suffixparams.getMap().put( VarFieldEnum.accesskey.toString(), user.getAccessKey() );
	 
		Map<String, String> pathPar =   urlparams.getMap();

		MultiHashMap        urlPar  =   (MultiHashMap) suffixparams.getMap();

		url = https_http_Url + url;

		if (!CollectionUtils.isEmpty(urlPar)) {
			url += RestUtils.parseMapUrlSuffixParams(urlPar);
		}

		if (CollectionUtils.isEmpty(pathPar)) {
 
			Map map = httpsRest.execute(url, HttpMethod.DELETE, null, responseExtractor); 
			return map;
		} else { 
			Map map = httpsRest.execute(url, HttpMethod.DELETE, null, responseExtractor, pathPar);
			return map;

		}

	}

	
 
	
	
	/**
	 * delete 请求 后 获得 响应;
	 */
	private ResponseExtractor<Map> responseExtractor = new ResponseExtractor<Map>() {
		@Override
		public Map extractData(ClientHttpResponse response) throws IOException {

			// ISO-8859-1
			// Reader
			Reader reader = new InputStreamReader(response.getBody(), "utf-8");

			String object = (String) IOUtils.readLines(reader).get(0);
			logger.info("        rsponse data string: " + object);

			Map map = gson.fromJson(object, Global.mapType);
			// 处理响应rest 响应 ; 
			RestUtils.handlerResponse(map);

			// Map map = gson.fromJson( reader, Global.mapType);

			logger.info("       rsponse map format: " + map);

			return map;

		}
	};

	private void validAccesskey(User user) {
		
		long  n = new Date().getTime() + 1000*60 ;
		
		boolean b =  ( user.expires == 0 ||  user.expires < n ) ; 
		logger.info("验证 accesskey是否过期 := " + b + "|--"+  user.expires +" ---" + n );
		
		if ( b ) {
			this.applyAccesskey(user);
		} 
	}

	/**
	 * 申请 accesskey ;
	 */
	public void applyAccesskey(User user) {

		// 申请accesskey ;
		HashMap<String, String> keybody = new HashMap<String, String>();

		keybody.put("account", user.getCompany_name());
		keybody.put("username", user.getUsername());
		keybody.put("password", user.getPassword());

		String url = https_http_Url + OrtherUrl.applyAccesskey;

		Map retkey = (Map) httpsRest.postForObject(url, RestUtils.createJosnBody(keybody), Map.class).get("ret");

		if (null != retkey) {
			user.setAccessKey(retkey.get("accesskey").toString());
			// 24 小时过期; 
			// {accessKey=68606d76a4e510f9daccd26849d5ac10, expires=1.438332063139E12}
			
			user.expires = new Date().getTime() + 1000 * 60*8 ;
			
			logger.info(" 申请 accesskey := " + retkey.get("accesskey") );
				

		} else {
			user.setAccessKey(null);
			throw new ExceptionMessage("申请 Accesskey 失败!");
		}

	}

}
