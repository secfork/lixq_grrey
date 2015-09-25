package com.sunwayland.rest.eneityV2;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.rest.basic.RestBody;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceModel extends RestBody {
	 
	@NotNull( groups={ Update.class })
	private String  uuid ; 
	
	@NotNull( groups={ Create.class})
	private String  name ; 
	private String  desc ; 
	private String  account_id ; 
	private String  driver_id ; 
	private String  driver_ver ; 
	private String  version ; 
	
	private String  point_count ; 
	private String  create_time ; 
	private String  last_modify_time ;
	
	
	
	
	
	
	
	
	
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
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}
	public String getDriver_ver() {
		return driver_ver;
	}
	public void setDriver_ver(String driver_ver) {
		this.driver_ver = driver_ver;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPoint_count() {
		return point_count;
	}
	public void setPoint_count(String point_count) {
		this.point_count = point_count;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getLast_modify_time() {
		return last_modify_time;
	}
	public void setLast_modify_time(String last_modify_time) {
		this.last_modify_time = last_modify_time;
	}  
	
	
	
	
	
	
	
	
}
