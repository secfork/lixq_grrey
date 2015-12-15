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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

public class HttpsRest {

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
	public Map get(User user, String url, GenericParams urlParams, GenericParams suffixParams) {

		validAccesskey(user);
		suffixParams = null == suffixParams ? SuffixParams.get() : suffixParams;
		urlParams = null == urlParams ? UrlParams.get() : urlParams;

		suffixParams.getMap().put(VarFieldEnum.accesskey.toString(), user.getAccessKey());

		Map<String, String> pathPar = urlParams.getMap();
		MultiHashMap urlPar = (MultiHashMap) suffixParams.getMap();

		url = https_http_Url + url;

		url += RestUtils.parseMapUrlSuffixParams(urlPar);

		if (CollectionUtils.isEmpty(pathPar)) {

			long currentTimeMillis = System.currentTimeMillis();

			Map map = httpsRest.getForObject(url, Map.class);

			long currentTimeMillis2 = System.currentTimeMillis();

			// logger.info("rest : GET  : url :" + url);
			
			logger.info("     : RESP : " + (currentTimeMillis2 - currentTimeMillis) + " : " + map);

			return map;

		} else {

			long currentTimeMillis = System.currentTimeMillis();
			Map map = httpsRest.getForObject(url, Map.class, pathPar);
			long currentTimeMillis2 = System.currentTimeMillis();

			// logger.info("rest : GET  : url :" + url);
			logger.info("     : RESP : " + (currentTimeMillis2 - currentTimeMillis) + " : " + map);

			return map;

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
	public Map put(User user, String url, Object body, GenericParams urlParams,
			GenericParams suffixParams) {

		validAccesskey(user);
		suffixParams = null == suffixParams ? SuffixParams.get() : suffixParams;
		urlParams = null == urlParams ? UrlParams.get() : urlParams;

		suffixParams.getMap().put(VarFieldEnum.accesskey.toString(), user.getAccessKey());

		Map<String, String> pathPar = urlParams.getMap();

		MultiHashMap urlPar = (MultiHashMap) suffixParams.getMap();

		url = https_http_Url + url;

		if (!CollectionUtils.isEmpty(urlPar)) {
			url += RestUtils.parseMapUrlSuffixParams(urlPar);
		}

		ResponseEntity<Map> exchange;

		long start = System.currentTimeMillis();

		if (CollectionUtils.isEmpty(pathPar)) {

			exchange = httpsRest.exchange(url, HttpMethod.PUT, RestUtils.createJosnBody(body), Map.class);

		} else {
			exchange = httpsRest.exchange(url, HttpMethod.PUT,
					RestUtils.createJosnBody(body), Map.class, pathPar);

		}
		long end = System.currentTimeMillis();

		Map body2 = exchange.getBody();

//		logger.info("rest : put : url : " + url);
		logger.info("     :     : body : " + body);
		logger.info("     : resp:" + (end - start) + " : " + body2);

		return body2;
	}

	/**
	 * 
	 * @param url
	 * @param body
	 *            根据实际情况 可传 null ;
	 * @return
	 */
	public Map post(User user,
			String url,
			Object body,
			GenericParams urlParams,
			GenericParams suffixParams
			) {

		validAccesskey(user);

		suffixParams = null == suffixParams ? SuffixParams.get() : suffixParams;
		urlParams = null == urlParams ? UrlParams.get() : urlParams;

		suffixParams.getMap().put(VarFieldEnum.accesskey.toString(), user.getAccessKey());

		Map<String, String> pathPar = urlParams.getMap();

		MultiHashMap urlPar = (MultiHashMap) suffixParams.getMap();

		url = https_http_Url + url;

		if (!CollectionUtils.isEmpty(urlPar)) {
			url += RestUtils.parseMapUrlSuffixParams(urlPar);
		}
		Map map;

		long start = System.currentTimeMillis();
		if (CollectionUtils.isEmpty(pathPar)) {
			map = httpsRest.postForObject(url,
					RestUtils.createJosnBody(body), Map.class);

		} else { 
			
			map = httpsRest.postForObject(url,
					RestUtils.createJosnBody(body), Map.class, pathPar);

			
		}
		long end = System.currentTimeMillis();
		
//		logger.info("rest : post : url " + url);
		
		logger.info("     :      : body" + body);
		logger.info("     : resp :" + (end - start) + " : " + map);

		return map;

	}

	/**
	 * //默认按 createtiem 倒叙排列; create_time 排序; query 分页数据; 返回: { total : xx ,
	 * data:[{},{}, ... ] } 数据 ;
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map query(User user,
			String restQueryUrl,
			GenericParams urlParams,
			GenericParams suffixParams_total,
			GenericParams suffixParams_data) {

		Map re = new HashMap();

		urlParams = null == urlParams ? UrlParams.get() : urlParams;
		suffixParams_total = null == suffixParams_total ? SuffixParams.get() : suffixParams_total;
		suffixParams_data = null == suffixParams_data ? SuffixParams.get() : suffixParams_data;

		// 默认 ; .sorts(FieldEnum.create_time, false) ;
		// Object object2 =
		// suffixParams_data.getParams().get(FieldEnum.create_time.toString());
		// if( null == object2){
		// suffixParams_data.sorts(FieldEnum.create_time, false);
		// }

		Map total = this.get(user, restQueryUrl, urlParams, suffixParams_total);

		Map data = this.get(user, restQueryUrl, urlParams, suffixParams_data);

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
	public Map delete(User user,
			String url,
			Object body,
			GenericParams urlparams,
			GenericParams suffixparams) {

		validAccesskey(user);

		suffixparams = null == suffixparams ? SuffixParams.get() : suffixparams;
		urlparams = null == urlparams ? UrlParams.get() : urlparams;

		suffixparams.getMap().put(VarFieldEnum.accesskey.toString(), user.getAccessKey());

		Map<String, String> pathPar = urlparams.getMap();

		MultiHashMap urlPar = (MultiHashMap) suffixparams.getMap();

		url = https_http_Url + url;

		if (!CollectionUtils.isEmpty(urlPar)) {
			url += RestUtils.parseMapUrlSuffixParams(urlPar);
		}

		Map map;

		long start = System.currentTimeMillis();

		if (CollectionUtils.isEmpty(pathPar)) {

			map = httpsRest.execute(url, HttpMethod.DELETE, null, responseExtractor);

		} else {
			  map = httpsRest.execute(url, HttpMethod.DELETE, null, responseExtractor, pathPar);
		}
		long end = System.currentTimeMillis();
		
//		logger.info("rest : delete : url :"+ url);
		logger.info("     : resp : "+( end -start)+" : "+ map);
		
		return map;
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
 

			Map map = gson.fromJson(reader, Global.mapType);
			// 处理响应rest 响应 ;
			RestUtils.handlerResponse(map);

			// Map map = gson.fromJson( reader, Global.mapType);

			logger.info("resp : rsponse " + map);

			return map;

		}

	};

	private void validAccesskey(User user) {

		long n = new Date().getTime() + 1000 * 60;

		boolean b = (user.expires == 0 || user.expires < n);

		if (b) {
			logger.info("accesskey失效重新申请, user =  " + user.toString());
			this.applyAccesskey(user, null);
		}
	}

	/**
	 * 申请 accesskey ; remoteip 可以传 null ;
	 */
	public void applyAccesskey(User user, String remoteIp) {

		// 申请accesskey ;
		HashMap<String, String> keybody = new HashMap<String, String>();

		keybody.put("account", user.getCompany_name());
		keybody.put("username", user.getUsername());
		keybody.put("password", user.getPassword());

		String url = https_http_Url + OrtherUrl.applyAccesskey;
 
		
		Map retkey = (Map) httpsRest.postForObject(url, RestUtils.createHttpRequestEntity(keybody, remoteIp), Map.class)
				.get("ret");

		if (null != retkey) {
			user.setAccessKey(retkey.get("accesskey").toString());
			// 24 小时过期;
			// {accessKey=68606d76a4e510f9daccd26849d5ac10,
			// expires=1.438332063139E12}

			user.expires = new Date().getTime() + 1000 * 60 * 8;
				
			logger.info(retkey);
			logger.info(" 申请 accesskey := " + retkey.get("accesskey"));

		} else {
			user.setAccessKey(null);
			throw new ExceptionMessage("申请 Accesskey 失败!");
		}

	}

}
