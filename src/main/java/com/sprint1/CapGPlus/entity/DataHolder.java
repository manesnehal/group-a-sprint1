package com.sprint1.CapGPlus.entity;

import javax.validation.constraints.NotBlank;

public class DataHolder {

	@NotBlank(message = "UserName is required")
	private String userName;

	@NotBlank(message = "Password is required")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
