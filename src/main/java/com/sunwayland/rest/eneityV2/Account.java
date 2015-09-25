package com.sunwayland.rest.eneityV2;

import javax.validation.constraints.NotNull;

 
public class Account {
	private String id ; 
	@NotNull
	private String name ; 
	private String desc ; 
	private String license ;  
	private String invite_code ; 
	 
	private String invitation_code ; 
	
	
	private String  add_alarm_space ;
	
	
	private String create_time ;
	
	
	//============ext====== 
	private  String  IdentifyCode ; 
	
//	private String  username ; 
//	private String  password ; 
	 
	
	
	/**
	 * 验证后手动 组装   admin  ; 
	 */
	private  User  admin   ;    // user ; 
	
	//============ext======
	
	
	
	public String getId() {
		return id;
	}
 
 
 
	public User getAdmin() {
		return admin;
	}



	public void setAdmin(User admin) {
		this.admin = admin;
	}



	public String getAdd_alarm_space() {
		return add_alarm_space;
	}
	public void setAdd_alarm_space(String add_alarm_space) {
		this.add_alarm_space = add_alarm_space;
	}
	public String getInvitation_code() {
		return invitation_code;
	}
	public void setInvitation_code(String invitation_code) {
		this.invitation_code = invitation_code;
	}
 
	
	
	 
	 
	 
	public String getIdentifyCode() {
		return IdentifyCode;
	}
	public void setIdentifyCode(String identifyCode) {
		IdentifyCode = identifyCode;
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
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getInvite_code() {
		return invite_code;
	}
	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
 
	
	
	
	
	
	
}
