package com.masa.common.googleplus;

public class GooglePerson {
	String id;
	String displayName;
	String email;
	
	public GooglePerson(String id, String displayName, String email) {
		this.id = id;
		this.displayName = displayName;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
