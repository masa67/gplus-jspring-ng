package com.masa.common.rest;

import com.masa.common.session.User;

public class UpdateResp {
	User user;

	public UpdateResp() {
	}

	public UpdateResp(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
