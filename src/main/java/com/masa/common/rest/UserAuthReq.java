package com.masa.common.rest;

public class UserAuthReq {
	private String state;
	private String code;

	public UserAuthReq() {
	}
	
	public UserAuthReq(String state, String code) {
		this.state = state;
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
