package com.sunwayland.rest.eneityV2;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.rest.basic.RestBody;


/**
 * system  联系人!
 * @author Administrator
 *
 */
public class Contact  extends RestBody{

    @NotNull( groups={ Update.class})
    private String   contact_id   ; 
    
    @NotNull( groups={ Create.class , Update.class})
    private String   system_id   ; 
    
    
    @NotNull( groups={ Create.class , Update.class})
    private String   last_name   ; 
    @NotNull( groups={ Create.class , Update.class})
    private String   first_name   ; 
 
    private String   mail_notice  ; 
    
    
    private String   sms_notice  ; 
    @NotNull( groups={ Create.class , Update.class})
    private String   email  ; 
    
    @Pattern( regexp="[0-9]{8,11}" , message="tel 必须是8-12的数字格式!!" ,
	     groups={ Create.class, Update.class})
    private String    tel  ; 
    
    private String    mobile_phone  ; 
    private String    country  ; 
    private String    province  ; 
    private String    city  ; 
    private String    address  ; 
    private String    zip_code  ;
    
    
    // 短信验证吗; 
    @NotNull
    private String  code ; 
    
    
    
    public String getContact_id() {
        return contact_id;
    }
    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }
    public String getSystem_id() {
        return system_id;
    }
    public void setSystem_id(String system_id) {
        this.system_id = system_id;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMobile_phone() {
        return mobile_phone;
    }
    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getZip_code() {
        return zip_code;
    }
    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }  
    
    
    
    
    
    
    
    
    
}
