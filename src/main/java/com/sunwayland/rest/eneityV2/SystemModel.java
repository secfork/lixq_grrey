package com.sunwayland.rest.eneityV2;

import java.util.Map;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunwayland.core.validate.EnumString;
import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.rest.basic.RestBody;


@JsonInclude( JsonInclude.Include.NON_NULL)
public class SystemModel extends RestBody {
 
	@NotNull( groups={ Update.class})
	private String uuid ; 
	
	@NotNull( groups={ Create.class})
	private String name ; 
	
	private String desc ; 
	
	private String mode ; 
	private String account_id ; 
	private String version ; 
	private String profile_count ; 
	private String device_count ; 
	private String create_time ; 
	private String last_modify_time ;
	
	// 2: gateway ;  1 :daserver ; 
	@EnumString(acceptedValues={"1" , "2"} , 
			 	groups={ Create.class, Update.class },
			 	message="must be 'Gateway' or 'DAServer' ")
	private String  comm_type; 
	

	private  String  gateway_default  ; 
	
	 
 
	public String getGateway_default() {
		return gateway_default;
	}
	public void setGateway_default(String gateway_default) {
		this.gateway_default = gateway_default;
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
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getProfile_count() {
		return profile_count;
	}
	public void setProfile_count(String profile_count) {
		this.profile_count = profile_count;
	}
	public String getDevice_count() {
		return device_count;
	}
	public void setDevice_count(String device_count) {
		this.device_count = device_count;
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
	public String getComm_type() {
		return comm_type;
	}
	public void setComm_type(String comm_type) {
		this.comm_type = comm_type;
	}  
	
	

	
	
	
}
