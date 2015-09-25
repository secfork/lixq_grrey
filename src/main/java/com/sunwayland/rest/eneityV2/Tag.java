package com.sunwayland.rest.eneityV2;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunwayland.core.validate.EnumString;
import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.rest.basic.RestBody;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tag  extends RestBody {
  
	public  interface profileLog {}; 
	
	 
	//============= 扩展 ============
	
	@NotNull( groups={ Create.class  ,  Update.class})
	private  String  system_model ;  // 创建 , 更新 ; systemModel  tag ;  systemModel 的 id; 
	 
	//=========================================================
	 
	
	@NotNull( groups={ Update.class  , profileLog.class})
	private String id ; 
	
	@NotNull( groups={Create.class})
	private String name ; 
	
	private String desc ; 
	private String tp_desc ;
	
	@NotNull( groups={ profileLog.class })
	private String profile ; 
	
	@NotNull( groups={ Create.class})
	private String type ; 
	
	private String unit ; 
	private String group ; 
	private String scale ; 
	private String deviation ; 
	private String save_log ; 
	
	private String log_cycle ; 
    
	
	private String log_type ; 
	
	private String connect ;
	
	 
	
	public String getSystem_model() {
		return system_model;
	}
	public void setSystem_model(String system_model) {
		this.system_model = system_model;
	}
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
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getDeviation() {
		return deviation;
	}
	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}
	public String getSave_log() {
		return save_log;
	}
	public void setSave_log(String save_log) {
		this.save_log = save_log;
	}
	public String getLog_cycle() {
		return log_cycle;
	}
	public void setLog_cycle(String log_cycle) {
		this.log_cycle = log_cycle;
	}
	public String getLog_type() {
		return log_type;
	}
	public void setLog_type(String log_type) {
		this.log_type = log_type;
	}
	public String getConnect() {
		return connect;
	}
	public void setConnect(String connect) {
		this.connect = connect;
	}
	public String getTp_desc() {
		return tp_desc;
	}
	public void setTp_desc(String tp_desc) {
		this.tp_desc = tp_desc;
	} 
	
	
	
	
}
