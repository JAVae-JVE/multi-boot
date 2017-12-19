package com.jinmark.sys.vo.permission;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author QC
 * @ClassName UserRequest
 * @Description TODO(用户表单域验证对象) 
 * @date 2017年10月13日上午11:23:35
 */
public class UserRequest {
	 private String id;
	 
	 @Length(min = 4, max = 20, message = "{user.username}")
	 private String username;
	 
	 @Length(max = 10, message = "{user.name}")
	 private String name;
	 private String mobile;
	 @NotEmpty(message = "{user.role}")
	 private String roleId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	 
	 
}
