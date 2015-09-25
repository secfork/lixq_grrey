package com.sunwayland.rest;

import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.sunwayland.rest.basic.HttpsRest;
import com.sunwayland.rest.eneityV2.DeviceModel;
import com.sunwayland.rest.eneityV2.Point;
import com.sunwayland.rest.eneityV2.User;
import com.sunwayland.rest.params.GenericParams;
import com.sunwayland.rest.params.SuffixParams;
import com.sunwayland.rest.params.UrlParams;
import com.sunwayland.rest.url.DeviceModelUrl;


@SuppressWarnings("rawtypes")
public class RestDeviceModel extends HttpsRest {

	public RestDeviceModel(RestTemplate restTemplate, String base_url) {
		super(restTemplate, base_url);
	}

	// ==========================================

 
 

	public Map queryDeviceModel( User user , GenericParams queryParams) {
		 
		return this.get( user ,DeviceModelUrl.query, UrlParams.get(), queryParams);

	}

	
	public Map updateDeviceModel( User user, DeviceModel deviceModel) {
		return this.put( user ,DeviceModelUrl.update, 
				deviceModel,
				UrlParams.get().device_model_id(deviceModel.getUuid()), 
				SuffixParams.get()
				);
	}

	public Map deleteDeviceModel( User user , String deviceModelId) { 
		if (null == deviceModelId)
			return null;
		
		return this.delete( user,
				DeviceModelUrl.delete, 
				null,
				UrlParams.get().device_model_id(deviceModelId),
				SuffixParams.get()
				);
	}

 

	public Map getAllPointOfDeviceModel( User user, String deviceModelid) {
		return this.get( user , 
				DeviceModelUrl.getAllPointOfDeviceModel,
				UrlParams.get().device_model_id(deviceModelid),
				SuffixParams.get().limit(Integer.MAX_VALUE));

	}



	public Map deletePointOfDeviceModel( User user, String deviceModelId, String pointId) {
		
		return this.delete( user ,
				DeviceModelUrl.deletePointOfDeviceModel, 
				null,
				UrlParams.get().device_model_id(deviceModelId).point_id(pointId), 
				SuffixParams.get() 
				);
	}

	@Deprecated
	public Map getByPk( User user, String pk) {
		if (null == pk)
			return null;
		return this.get( user , 
				         DeviceModelUrl.select, 
				         UrlParams.get().device_model_id(pk),
				         SuffixParams.get() );

	}

}
