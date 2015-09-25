package com.sunwayland.rest.eneityV2;

public class Role {
	
	private String id ; 
	private String account_id ; 
	private String name ; 
	private String[] privilege ;
	private String role_category ; // 0, 账户角色，1区域角色
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String[] privilege) {
		this.privilege = privilege;
	}
	public String getRole_category() {
		return role_category;
	}
	public void setRole_category(String role_category) {
		this.role_category = role_category;
	}
 
	
	
	
	
	
}
