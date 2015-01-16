package com.masa.common.session;

public class Session {
	String sessionId;
	String userId;
	String state;
	
	public Session(String sessionId, String userId, String state) {
		this.sessionId = sessionId;
		this.userId = userId;
		this.state = state;
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
}
