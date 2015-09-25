package com.sunwayland.rest.url;

public class DeviceModelUrl {
  
	public static final String create   = "/devmodels";
	@Deprecated
	public static final String select   = "/devmodels/{device_model_id}";
	public static final String delete   = "/devmodels/{device_model_id}";
	public static final String update   = "/devmodels/{device_model_id}";

	
	public static final String query    = "/query/devmodels";
	
	 
	
	
	public static final String  addPoint2DeviceModel =      "/devmodels/{device_model_id}/points";
	public static final String  getAllPointOfDeviceModel =  "/devmodels/{device_model_id}/points";
	public static final String  getPointOfDeviceModel = 	"/devmodels/{device_model_id}/points/{point_id}";
	public static final String  updatePointOfDeviceModel =  "/devmodels/{device_model_id}/points/{point_id}";
	public static final String  deletePointOfDeviceModel =  "/devmodels/{device_model_id}/points/{point_id}";
	
	
	
	
}
