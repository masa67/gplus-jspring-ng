package com.masa.common.rest;

public class UserReq {
	private String nickname;

	public UserReq() {
	}

	public UserReq(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
