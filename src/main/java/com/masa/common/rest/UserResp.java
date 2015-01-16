
package com.masa.common.rest;

import com.masa.common.session.User;
import com.masa.common.googleplus.GoogleSignIn;

public class UserResp {
	User user;
	GoogleSignIn googleSignIn;

	public UserResp() {
	}
	
	public GoogleSignIn getGoogleSignIn() {
		return googleSignIn;
	}

	public void setGoogleSignIn(GoogleSignIn googleSignIn) {
		this.googleSignIn = googleSignIn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
