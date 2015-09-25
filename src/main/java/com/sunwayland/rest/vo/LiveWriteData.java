package com.sunwayland.rest.vo;

import javax.validation.constraints.NotNull;

public class LiveWriteData {
	
	@NotNull
	private String system_id ; 
	@NotNull
	private String tagname; 
	@NotNull
	private String value ;
	
	
	public String getSystem_id() {
		return system_id;
	}
	public void setSystem_id(String system_id) {
		this.system_id = system_id;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	} 
	
	 
	
	
}
