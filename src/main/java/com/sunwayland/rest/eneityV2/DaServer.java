package com.sunwayland.rest.eneityV2;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunwayland.rest.basic.RestBody;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DaServer  extends RestBody {
  private String id ; 
  private String uuid ; 
  private String name ; 
  private String desc ; 
  private String hostname ; 
  private String ip ; 
  private String port ; 
  private HashMap<String, Object> params ; 
  
  private String max_stations ; 
  private String total_stations ; 
  private String active_stations ; 
  private String xmpp_id ; 
  private String xmpp_domain ; 
  private String xmpp_user ; 
  private String xmpp_passwd ;
  
  
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
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
public String getHostname() {
	return hostname;
}
public void setHostname(String hostname) {
	this.hostname = hostname;
}
public String getIp() {
	return ip;
}
public void setIp(String ip) {
	this.ip = ip;
}
public String getPort() {
	return port;
}
public void setPort(String port) {
	this.port = port;
}
 
public HashMap<String, Object> getParams() {
	return params;
}
public void setParams(HashMap<String, Object> params) {
	this.params = params;
}
public String getMax_stations() {
	return max_stations;
}
public void setMax_stations(String max_stations) {
	this.max_stations = max_stations;
}
public String getTotal_stations() {
	return total_stations;
}
public void setTotal_stations(String total_stations) {
	this.total_stations = total_stations;
}
public String getActive_stations() {
	return active_stations;
}
public void setActive_stations(String active_stations) {
	this.active_stations = active_stations;
}
public String getXmpp_id() {
	return xmpp_id;
}
public void setXmpp_id(String xmpp_id) {
	this.xmpp_id = xmpp_id;
}
public String getXmpp_domain() {
	return xmpp_domain;
}
public void setXmpp_domain(String xmpp_domain) {
	this.xmpp_domain = xmpp_domain;
}
public String getXmpp_user() {
	return xmpp_user;
}
public void setXmpp_user(String xmpp_user) {
	this.xmpp_user = xmpp_user;
}
public String getXmpp_passwd() {
	return xmpp_passwd;
}
public void setXmpp_passwd(String xmpp_passwd) {
	this.xmpp_passwd = xmpp_passwd;
}  
  
  
  
  
  
  
	
}
