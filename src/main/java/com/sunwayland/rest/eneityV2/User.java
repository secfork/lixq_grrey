package com.sunwayland.rest.eneityV2;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.core.validate.type.VerifyEmail;
import com.sunwayland.core.validate.type.VerifyPhone;
 

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

	
	
	@NotNull( groups={ Update.class , VerifyPhone.class , VerifyEmail.class })
	private String  id ; 
	private String  account_id ; 
	private String  desc ; 
	
	
	@NotNull( groups={ Create.class})
	private String  username ; 
	@NotNull( groups={ Create.class})
	private String  password ; 
	private String  is_super_user ; 
	
	private String  sex ;
	private String  first_name ;
	private String  last_name ;

	private String nickname ;

	private String birthday ; 
	
	
	private String  address ; 
	
	@Email( groups ={ Create.class , VerifyEmail.class } )
	private String  email ; 
	private String email_verified;  // 0 :|| 1;
	
	@NotNull( groups={ VerifyPhone.class} ) 
	private String  mobile_phone ; 
	
	private String mobile_phone_verified;  // 0 :|| 1;

	
	private String  last_login_ip ; 
	private String  mail_notice ; 

	private String  sms_notice ; 
	
	private String  create_time ; 
	
	private String  wechat_notice ; 
	
	private String last_login_time ;
	
	
	
	
  //============= ext =============
 
	private String  company_name;  // company_name ; 
	
	@JsonIgnore
	public long  expires ; 
	
	@JsonIgnore
	private String accessKey ; 
	
	//验证码; 
	@NotNull( groups={ VerifyPhone.class })
	private String  verifi ; 
	
	@JsonIgnore
	private Long   sendVerifyEmailTime = 0L;
	
	//============= ext =============
	
	 
	
	public String getId() {
		return id;
	}
 
	public long getSendVerifyEmailTime() {
		return sendVerifyEmailTime;
	}

	public void setSendVerifyEmailTime(Long sendVerifyEmailTime) {
		this.sendVerifyEmailTime = sendVerifyEmailTime;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail_verified() {
		return email_verified;
	}
	public void setEmail_verified(Boolean email_verified) {
		this.email_verified = email_verified?"1":"0";
	}
	public String getMobile_phone_verified() {
		return mobile_phone_verified;
	}
	public void setMobile_phone_verified(Boolean mobile_phone_verified) {
		this.mobile_phone_verified = mobile_phone_verified?"1":"0";
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
	 
	 
//	public String getSex() {
//		return sex;
//	}
//	public void setSex(String sex) {
//		this.sex = sex;
//	}
	
	
	
 
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

//	@Override
//	public String toString() {
//		 
//		return  "[" + this.getCompany_name() +","+ this.getUsername() +"]" ;
//		
//	}
// 
 
	
	
	
	
}
