package com.sunwayland.core.vo;

public class FieldErrorMsg {
	private String field;
	private String msg;

	public FieldErrorMsg() {
		super();
	}

	public FieldErrorMsg(String field, String msg) {
		super();
		this.field = field;
		this.msg = msg;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
