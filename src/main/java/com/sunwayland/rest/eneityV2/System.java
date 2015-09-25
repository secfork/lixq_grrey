package com.sunwayland.rest.eneityV2;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunwayland.core.validate.type.Create;
import com.sunwayland.core.validate.type.Update;
import com.sunwayland.rest.basic.RestBody;


 @JsonInclude( JsonInclude.Include.NON_NULL ) 
 
public class System  extends RestBody{

	 @NotNull( groups={ Update.class})
	private  String uuid ; 

	@NotNull( groups={Create.class}) 
	private  String name ; 
	private  String desc ; 
	
	@NotNull( groups={ Create.class})
	private  String model ; 
	

	@NotNull( groups={ Create.class})
	private  String profile ; 
	
	  
	@NotNull( groups={ Create.class })
	private  String region_id ;  // 所属的 区域 ; 
	
	
	private  String state ; 
	 

	private  String network ; 
	private  String gateway  ; 
	
	
	
	
	private  String account_id ; 
	
	private  String daserver_id ;
	
	//@NotNull( groups={ Create.class })
	//private  String sn ; 
	
	
	//经纬度
	private  String longitude ; 
	private  String latitude ; 
	
	private  String pos_type ; 
	private  String version ; 
	private  String create_time ; 
	private  String last_modify_time ; 
	private  String last_sync_time ;
	
	
	
	
	
	
	
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	 
	
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
 
 
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getDaserver_id() {
		return daserver_id;
	}
	public void setDaserver_id(String daserver_id) {
		this.daserver_id = daserver_id;
	}
 
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getPos_type() {
		return pos_type;
	}
	public void setPos_type(String pos_type) {
		this.pos_type = pos_type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getLast_modify_time() {
		return last_modify_time;
	}
	public void setLast_modify_time(String last_modify_time) {
		this.last_modify_time = last_modify_time;
	}
	public String getLast_sync_time() {
		return last_sync_time;
	}
	public void setLast_sync_time(String last_sync_time) {
		this.last_sync_time = last_sync_time;
	}  
	
	
	
	
	
	
}
