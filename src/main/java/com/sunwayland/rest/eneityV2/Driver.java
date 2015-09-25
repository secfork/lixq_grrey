package com.sunwayland.rest.eneityV2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunwayland.rest.basic.RestBody;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Driver extends RestBody{

	
	private String id ; 
	private String uuid ; 
	private String name ; 
	private String desc ; 
	
	private String path ; 
	private String version ; 
	private String comm_mode_support ; 
	private String device_params ; 
	private String point_params ; 
	private String io_params ;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getComm_mode_support() {
		return comm_mode_support;
	}
	public void setComm_mode_support(String comm_mode_support) {
		this.comm_mode_support = comm_mode_support;
	}
	public String getDevice_params() {
		return device_params;
	}
	public void setDevice_params(String device_params) {
		this.device_params = device_params;
	}
	public String getPoint_params() {
		return point_params;
	}
	public void setPoint_params(String point_params) {
		this.point_params = point_params;
	}
	public String getIo_params() {
		return io_params;
	}
	public void setIo_params(String io_params) {
		this.io_params = io_params;
	} 
	
	
	
}
