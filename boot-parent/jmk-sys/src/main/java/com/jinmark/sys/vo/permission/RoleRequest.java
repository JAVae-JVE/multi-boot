package com.jinmark.sys.vo.permission;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author QC
 * @ClassName RoleRequest
 * @Description TODO(角色表单域验证对象) 
 * @date 2017年10月13日上午11:29:05
 */
public class RoleRequest {
	private String id;
	/**
	 * 角色名称
	 */
	@Length(min = 2, max = 20, message = "{role.name}")
	private String roleName;
	/**
	 * 角色标识
	 */
	@Length(min = 2, max = 20, message = "{role.flag}")
	private String roleFlag;
	/**
	 * 角色描述
	 */
	@Length(max = 100, message = "{role.description}")
	private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleFlag() {
		return roleFlag;
	}
	public void setRoleFlag(String roleFlag) {
		this.roleFlag = roleFlag;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
