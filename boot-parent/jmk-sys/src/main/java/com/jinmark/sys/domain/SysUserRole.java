package com.jinmark.sys.domain;
// Generated 2017-4-13 17:12:14 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * SysUserRole generated by hbm2java
 */
@Entity
@Table(name = "sys_user_role")
public class SysUserRole implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7409702841676074149L;
	private String id;
	private SysUser sysUser;
	private SysRole sysRole;

	public SysUserRole() {
	}

	public SysUserRole(SysUser sysUser, SysRole sysRole) {
		this.sysUser = sysUser;
		this.sysRole = sysRole;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

}
