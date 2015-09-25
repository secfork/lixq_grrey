package com.sunwayland.rest.eneityV2;

import javax.validation.constraints.NotNull;

public class User {

	
	private String  id ; 
	private String  account_id ; 
	private String  desc ; 
	@NotNull
	private String  username ; 
	@NotNull
	private String  password ; 
	private String  is_super_user ; 
	private String  address ; 
	private String  email ; 
	private String  last_login_ip ; 
	private String  mail_notice ; 
	private String  sms_notice ; 
	private String  wechat_notice ; 
	private String  create_time ; 
	private String last_login_time ;
	
	private String  mobile_phone ; 
	
	
  //============= ext =============
	private String  company_name;  // company_name ; 
	
	public Long  expires ; 
	
	private String accessKey ; 
	
	//验证码; 
	private String  verifi ; 
	
	
	//============= ext =============
	
	 
	
	public String getId() {
		return id;
	}
	public String getMobile_phone() {
		return mobile_phone;
	}
	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}
	public String getVerifi() {
		return verifi;
	}
	public void setVerifi(String verifi) {
		this.verifi = verifi;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public Long getExpires() {
		return expires;
	}
	public void setExpires(Long expires) {
		this.expires = expires;
	}
	 
	 
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIs_super_user() {
		return is_super_user;
	}
	public void setIs_super_user(String is_super_user) {
		this.is_super_user = is_super_user;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLast_login_ip() {
		return last_login_ip;
	}
	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}
	public String getMail_notice() {
		return mail_notice;
	}
	public void setMail_notice(String mail_notice) {
		this.mail_notice = mail_notice;
	}
	public String getSms_notice() {
		return sms_notice;
	}
	public void setSms_notice(String sms_notice) {
		this.sms_notice = sms_notice;
	}
	public String getWechat_notice() {
		return wechat_notice;
	}
	public void setWechat_notice(String wechat_notice) {
		this.wechat_notice = wechat_notice;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}
 
 
	
	
	
	
}
