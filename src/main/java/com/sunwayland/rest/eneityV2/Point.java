package com.sunwayland.rest.eneityV2;

import java.util.HashMap;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.rest.basic.RestBody;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Point extends RestBody {

	@NotNull( groups={ Update.class})
	private String id ; 
	
	@NotNull( groups={Create.class})
	private String name ; 
	
	private String desc ; 
	
	@NotNull( groups={Create.class , Update.class})
	private String device_model ; 
	
	
	private String type ; 
	private String readwrite ; 
	private HashMap<String, Object> params ;
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getDevice_model() {
		return device_model;
	}
	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReadwrite() {
		return readwrite;
	}
	public void setReadwrite(String readwrite) {
		this.readwrite = readwrite;
	}
	public HashMap<String, Object> getParams() {
		return params;
	}
	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}
  
	
}
