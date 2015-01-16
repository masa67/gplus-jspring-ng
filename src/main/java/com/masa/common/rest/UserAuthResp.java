package com.masa.common.rest;

import com.masa.common.session.User;

public class UserAuthResp {
	User user;
	
	public UserAuthResp(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
