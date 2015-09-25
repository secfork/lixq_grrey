package com.sunwayland.rest.eneityV2;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Select;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.rest.basic.RestBody;

@JsonInclude( JsonInclude.Include.NON_NULL)
public class Profile  extends RestBody {
 
	
	@NotNull( groups={ Update.class  , Select.class})
	private  String  uuid ; 
	
	@NotNull( groups={ Create.class})
	private  String  name ; 
	
	private  String  desc ; 
	
	@NotNull( groups={ Create.class })
	private  String  system_model ; 
	
	private  String  version ; 
	private  String  tag_count ; 
	private  String  trigger_count ; 
	private  String  create_time ; 
	private  String  last_modify_time ;
	
	
	
	
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
	public String getSystem_model() {
		return system_model;
	}
	public void setSystem_model(String system_model) {
		this.system_model = system_model;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTag_count() {
		return tag_count;
	}
	public void setTag_count(String tag_count) {
		this.tag_count = tag_count;
	}
	public String getTrigger_count() {
		return trigger_count;
	}
	public void setTrigger_count(String trigger_count) {
		this.trigger_count = trigger_count;
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
