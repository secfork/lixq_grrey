package com.sunwayland.rest.url;

public class SystemModelUrl   {
	
	
	public static final String create = "/sysmodels";
	public static final String delete = "/sysmodels/{system_model_id}";
	public static final String select = "/sysmodels/{system_model_id}";
	public static final String update = "/sysmodels/{system_model_id}";

	public static final String query = "/query/sysmodels";
	
	
	
	public static final String addDevice2SM =       "/sysmodels/{system_model_id}/devices";
	public static final String selectAllDeviceOfSM = "/sysmodels/{system_model_id}/devices";
	public static final String selectDeviceOfSM =    "/sysmodels/{system_model_id}/devices/{device_id}";
	public static final String updateDeviceOfSM =    "/sysmodels/{system_model_id}/devices/{device_id}";
	public static final String deleteDeviceOfSM =    "/sysmodels/{system_model_id}/devices/{device_id}";
	
	
	public static final String  addProfile2SM =  		 "/profiles";
	public static final String  slelectAllProfileOfSM =  "/profiles";
	public static final String  selectProfileOfSM =      "/profiles/{profile_id}";
	public static final String  updateProfileOfSM =      "/profiles/{profile_id}";
	 
	public static final String  deleteProfile2System =   "/profiles/{profile_id}";
	
	
	
	public static final String  addTag2SM =  		    "/sysmodels/{system_model_id}/tags";
	public static final String  selectAllTagOfSM =     "/sysmodels/{system_model_id}/tags";
	public static final String  deleteTagOfSM =         "/sysmodels/{system_model_id}/tags/{tag_id}";
	public static final String  updateTagOfSM =         "/sysmodels/{system_model_id}/tags/{tag_id}";
	
	
	public static String createSmMessage = "/profiles/{profile_id}/messages";
	public static String getSmMessage    = "/profiles/{profile_id}/messages";
	public static String deleteSmMessage = "/profiles/{profile_id}/messages/{msgprofile_id}";
	public static String updataSmMessage = "/profiles/{profile_id}/messages/{msgprofile_id}";
	
	


}
