package com.sunwayland.rest.eneityV2;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.rest.basic.RestBody;

public class SystemModelDevice extends RestBody {
	
	
	 
	@NotNull( groups={Update.class})
	private String  id ; 
	
	@NotNull(groups={ Create.class})
	private String  name ; 
	
	private String  desc ; 
	
	@NotNull( groups={ Create.class  , Update.class })
	private String  system_model ; 
	
	@NotNull( groups={ Create.class })
	private String  device_model ;  // device_model 的  id ; 
	
	private String  params ; 
	private String  network ;
	
	 
	 
	@Range( min=1 , groups={ Create.class , Update.class})
	private Integer  dev_cycle ; 
	
	@Range( min=1 , max=3, groups={ Create.class , Update.class})
	private Integer  dev_retry ; 
	
	@Range( min=3 , max=30 , groups={ Create.class , Update.class})
	private Integer  dev_timeout ; 
	
	
	
	
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
	public String getSystem_model() {
		return system_model;
	}
	public void setSystem_model(String system_model) {
		this.system_model = system_model;
	}
	public String getDevice_model() {
		return device_model;
	}
	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}
  
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public Integer getDev_cycle() {
		return dev_cycle;
	}
	public void setDev_cycle(Integer dev_cycle) {
		this.dev_cycle = dev_cycle;
	}
	public Integer getDev_retry() {
		return dev_retry;
	}
	public void setDev_retry(Integer dev_retry) {
		this.dev_retry = dev_retry;
	}
	public Integer getDev_timeout() {
		return dev_timeout;
	}
	public void setDev_timeout(Integer dev_timeout) {
		this.dev_timeout = dev_timeout;
	}
 
	
	
	
}
