package com.jinmark.sys.domain;
// Generated 2017-4-13 17:12:14 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * SysRole generated by hbm2java
 */
@Entity
@Table(name = "sys_role", uniqueConstraints = { @UniqueConstraint(columnNames = "role_name"),
		@UniqueConstraint(columnNames = "role_flag") })
public class SysRole implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3354908549203723965L;
	private String id;
	private String roleName;
	private String roleFlag;
	private String description;
	private boolean available;
	private Date createtime;
	private Set<SysRolePermission> sysRolePermissions = new HashSet<SysRolePermission>(0);

	public SysRole() {
	}

	
	public SysRole(String id) {
		super();
		this.id = id;
	}


	public SysRole(String id, String roleName, String roleFlag, String description) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.roleFlag = roleFlag;
		this.description = description;
	}


	public SysRole(String roleName, String roleFlag, boolean available, Date createtime) {
		this.roleName = roleName;
		this.roleFlag = roleFlag;
		this.available = available;
		this.createtime = createtime;
	}

	public SysRole(String roleName, String roleFlag, String description, boolean available, Date createtime,
			Set<SysRolePermission> sysRolePermissions) {
		this.roleName = roleName;
		this.roleFlag = roleFlag;
		this.description = description;
		this.available = available;
		this.createtime = createtime;
		this.sysRolePermissions = sysRolePermissions;
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

	@Column(name = "role_name", unique = true, nullable = false, length = 64)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "role_flag", unique = true, nullable = false, length = 32)
	public String getRoleFlag() {
		return this.roleFlag;
	}

	public void setRoleFlag(String roleFlag) {
		this.roleFlag = roleFlag;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "available", nullable = false)
	public boolean isAvailable() {
		return this.available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysRole")
	public Set<SysRolePermission> getSysRolePermissions() {
		return this.sysRolePermissions;
	}

	public void setSysRolePermissions(Set<SysRolePermission> sysRolePermissions) {
		this.sysRolePermissions = sysRolePermissions;
	}

}
