package com.sunwayland.rest.basic;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
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
import com.sunwayland.rest.RestUtils;
import com.sunwayland.rest.params.GenericParams;
import com.sunwayland.web.vo.Global;

public class HttpRest {

	public Gson gson = new GsonBuilder().serializeNulls().create();

	Logger logger = Logger.getLogger(HttpsRest.class);
	// new Gson() ;

	public RestTemplate httpsRest;
	public String http_http_Url;

	public HttpRest() {
		// GenericRest
		super();
	}

	public HttpRest(RestTemplate restTemplate, String base_url) {
		this.httpsRest = restTemplate;
		this.http_http_Url = base_url;
	}

	@SuppressWarnings("rawtypes")
	public Map get(String url, GenericParams urlParams, GenericParams suffixParams) {

		Map<String, String> pathPar = urlParams.getMap();
		MultiHashMap urlPar = (MultiHashMap) suffixParams.getMap();

		url = http_http_Url + url;

		url += RestUtils.parseMapUrlSuffixParams(urlPar);

		if (CollectionUtils.isEmpty(pathPar)) {
			return httpsRest.getForObject(url, Map.class);
		} else {
			return httpsRest.getForObject(url, Map.class, pathPar);

		}

	};

	public Map put(String url, Object body, GenericParams urlParams, GenericParams suffixParams) {

		Map<String, String> pathPar = urlParams.getMap();

		MultiHashMap urlPar = (MultiHashMap) suffixParams.getMap();

		url = http_http_Url + url;

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

	public Map post(  
			String url,
			Object body,
			GenericParams urlParams,
			GenericParams suffixParams
			) {

		 
		Map<String, String> pathPar = urlParams.getMap();

		MultiHashMap urlPar = (MultiHashMap) suffixParams.getMap();

		url = http_http_Url + url;

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
	
	
	public Map delete( 
			String url,
			Object body,
			GenericParams urlparams,
			GenericParams suffixparams) {

	 
		Map<String, String> pathPar = urlparams.getMap();

		MultiHashMap urlPar = (MultiHashMap) suffixparams.getMap();

		url = http_http_Url + url;

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
	
	 
	
	private ResponseExtractor<Map> responseExtractor = new ResponseExtractor<Map>() {
		@Override
		public Map extractData(ClientHttpResponse response) throws IOException {

			// ISO-8859-1
			// Reader
			Reader reader = new InputStreamReader(response.getBody(), "utf-8");

			String object = (String) IOUtils.readLines(reader).get(0);
			logger.info("        rsponse data string: " + object);

			Map map = gson.fromJson(object, Global.mapType);
			RestUtils.handlerResponse(map);

			// Map map = gson.fromJson( reader, Global.mapType);

			logger.info("       rsponse map format: " + map);

			return map;

		}
	};
	
	
	
	
	

}
