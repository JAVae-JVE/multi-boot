package com.jinmark.sys.vo.permission;


public class UserResponse {
	private String id;
	private String name;
	private String username;
	private boolean locked;
	private String mobile;
	private String roleId;
	
	
	public UserResponse() {
		super();
	}
	public UserResponse(String id, String name, String username, boolean locked, String mobile, String roleId) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.locked = locked;
		this.mobile = mobile;
		this.roleId = roleId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
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
