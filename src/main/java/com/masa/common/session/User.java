package com.masa.common.session;

import com.masa.common.googleplus.GooglePerson;

public class User {
	String userId;
	String nickname;
    GooglePerson googlePerson;

	public User(String userId, String nickname, GooglePerson googlePerson) {
		this.userId = userId;
		this.nickname = nickname;
		this.googlePerson = googlePerson;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
    	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public GooglePerson getGooglePerson() {
		return googlePerson;
	}

	public void setGooglePerson(GooglePerson googlePerson) {
		this.googlePerson = googlePerson;
	}
	
	
}
