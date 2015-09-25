package com.sunwayland.rest.eneityV2;

public class UserGroup {
	
	
	private String  id ; 
	private String  name ; 
	private String  account_id ; 
	private String  is_default ; 
	private String  default_privilege ; 
	private String  desc ;
	
	
	
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
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getIs_default() {
		return is_default;
	}
	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}
	public String getDefault_privilege() {
		return default_privilege;
	}
	public void setDefault_privilege(String default_privilege) {
		this.default_privilege = default_privilege;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}  
	
	
	
	

}
