package com.sunwayland.rest.eneityV2;

import javax.validation.constraints.NotNull;

import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.rest.basic.RestBody;

public class ProfileMessage extends RestBody {
	
	@NotNull( groups={ Update.class })
	private String  message_id   ; 
	
	@NotNull(groups={ Create.class})
	private  String name ; 
	
	
//	@NotNull(groups={ Create.class})
	private String  user_id ; 

	@NotNull(groups={ Create.class})
	private String  user_category ; 

	@NotNull(groups={ Create.class})
	private String  trigger_id ; 
	 
	@NotNull( groups= { Create.class , Update.class })
	private String  profile ; 
	
	
	private String  message_params ;


	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getUser_category() {
		return user_category;
	}


	public void setUser_category(String user_category) {
		this.user_category = user_category;
	}


	public String getTrigger_id() {
		return trigger_id;
	}


	public void setTrigger_id(String trigger_id) {
		this.trigger_id = trigger_id;
	}


	public String getProfile() {
		return profile;
	}


	public void setProfile(String profile) {
		this.profile = profile;
	}


	public String getMessage_params() {
		return message_params;
	}


	public void setMessage_params(String message_params) {
		this.message_params = message_params;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}  
		
	
	
	
	
	
	
	
	
	
	
	
	
}
