package com.sunwayland.rest.basic;

public enum SystemState {

	unactive("0") ,
	active("1") ,
	stStandby("2");
	
	private String value ;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private SystemState(String value) {
		this.value = value;
	}
	
	
	
	
}
