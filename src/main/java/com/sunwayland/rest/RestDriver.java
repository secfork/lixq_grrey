package com.sunwayland.rest;

import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.sunwayland.rest.basic.HttpsRest;
import com.sunwayland.rest.eneityV2.Driver;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.DriverUrl;


@SuppressWarnings("rawtypes")
public class RestDriver extends HttpsRest {

	private RestTemplate restTemplate;
	private String base_url;

	public RestDriver(RestTemplate restTemplate, String base_url) {
		super();
		this.restTemplate = restTemplate ;
		this.base_url = base_url ;
	}

	// ======================================

	
	public Map createDriver( User user, String driverName, Driver driver) {
		return this.post( user , 
							DriverUrl.create, 
							driver, 
							UrlParams.get(),
							SuffixParams.get().name(driverName) );
	}

	public Map deleteDriver( User user, String driver_uuid) {
		return this.delete( user, 
							DriverUrl.delete,
							null, 
							UrlParams.get().driver_uuid(driver_uuid), 
							SuffixParams.get());
	}

}
