package com.jinmark.sys.vo.permission;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author QC
 * @ClassName MenuRequest
 * @Description TODO(菜单表单域验证对象) 
 * @date 2017年10月13日上午10:38:47
 */
public class MenuRequest {
	/**
	 * 
	 */
	private String id;
	/**
	 * 菜单名称
	 */
	@Length(min = 2, max = 20, message = "{menu.name}")
	private String name;
	/**
	 * 菜单类型
	 */
	@NotEmpty(message = "{menu.type}")
	private String resourceType;
	/**
	 * 菜单URL地址
	 */
	private String url;
	/**
	 * 菜单权限字符串
	 */
	private String permission;
	/**
	 * 父级菜单id
	 */
	@NotEmpty(message = "{menu.parentId}")
	private String parentId;
	/**
	 * 顺序
	 */
	private Integer priority;
	/**
	 * 菜单图标class
	 */
	private String iconClass;
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
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	
	
}
