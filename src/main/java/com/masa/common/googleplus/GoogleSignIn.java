package com.masa.common.googleplus;

public class GoogleSignIn {
	String clientId;
	String state;
	
	public GoogleSignIn(String clientId, String state) {
		this.clientId = clientId;
		this.state = state;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
