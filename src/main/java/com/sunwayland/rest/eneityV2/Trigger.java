package com.sunwayland.rest.eneityV2;

import javax.validation.constraints.NotNull;

import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.rest.basic.RestBody;

public class Trigger  extends RestBody{
	
	@NotNull( groups={ Update.class  })
	private String  id ; 
	 
	@NotNull( groups={Create.class})
	private String  name ; 
	
	private String  desc ; 
	
	@NotNull(groups={ Update.class,  Create.class })
	private String  profile ; 
	
	@NotNull( groups={ Create.class })
	private String  action ;  // 报警,  事件, 任务; 
	
	@NotNull( groups={ Create.class })
	private String  origin ;
	
	@NotNull( groups={ Create.class})
	private  String conditions ;  
	 
	
	@NotNull( groups={ Create.class})
	private  String  params ; 

	@NotNull( groups={ Create.class})
	private  String  type ; // 一次触发, 多次触发; 
	
	
	//============================================================================
	
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
	public String getProfile() {
		return profile;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
  
 
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	 
 
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	} 
	
	
	
	
}
