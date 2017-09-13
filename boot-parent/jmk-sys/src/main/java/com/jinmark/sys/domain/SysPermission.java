package com.jinmark.sys.domain;
// Generated 2017-4-13 17:12:14 by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * SysPermission generated by hbm2java
 */
@Entity
@Table(name = "sys_permission")
public class SysPermission implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6151923742774760345L;
	private String id;
	private String name;
	private String resourceType;
	private String url;
	private String permission;
	private String parentId;
	private boolean available;
	private Integer priority;
	private Date createtime;

	public SysPermission() {
	}

	public SysPermission(String name, String resourceType, boolean available, Date createtime) {
		this.name = name;
		this.resourceType = resourceType;
		this.available = available;
		this.createtime = createtime;
	}

	public SysPermission(String name, String resourceType, String url, String permission, String parentId,
			boolean available, Integer priority, Date createtime) {
		this.name = name;
		this.resourceType = resourceType;
		this.url = url;
		this.permission = permission;
		this.parentId = parentId;
		this.available = available;
		this.priority = priority;
		this.createtime = createtime;
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")

	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "resource_type", nullable = false, length = 20)
	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "permission", length = 64)
	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Column(name = "parentId")
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "available", nullable = false)
	public boolean isAvailable() {
		return this.available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Column(name = "priority")
	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}


}