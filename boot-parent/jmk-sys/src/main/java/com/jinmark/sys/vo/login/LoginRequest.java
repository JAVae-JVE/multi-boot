package com.jinmark.sys.vo;

import org.hibernate.validator.constraints.Length;

public class LoginRequest {
	/**
	 * 账号
	 */
	@Length(min = 5, max = 20, message = "{login.username}")
	private String username;
	/**
	 * 密码
	 */
	@Length(min = 6, message = "{login.password}")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
