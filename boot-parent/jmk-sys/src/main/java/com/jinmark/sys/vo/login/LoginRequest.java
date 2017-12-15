package com.jinmark.sys.vo.login;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author QC
 * @ClassName LoginRequest
 * @Description TODO(登录表单域验证对象) 
 * @date 2017年10月10日下午3:55:06
 */
public class LoginRequest {
	/**
	 * 账号
	 */
	@Length(min = 4, max = 20, message = "{user.username}")
	private String username;
	/**
	 * 密码
	 */
	@Length(min = 8, message = "{user.password}")
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
