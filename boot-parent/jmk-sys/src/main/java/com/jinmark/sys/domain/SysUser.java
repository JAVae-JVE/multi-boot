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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * SysUser generated by hbm2java
 */
@Entity
@Table(name = "sys_user", uniqueConstraints = { @UniqueConstraint(columnNames = "mobile"),
		@UniqueConstraint(columnNames = "username") })
public class SysUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -572839489678207988L;
	private String id;
	private String name;
	private String username;
	private String password;
	private String salt;
	private boolean locked;
	private String mobile;
	private Date createtime;
	private Set<SysUserRole> sysUserRoles = new HashSet<SysUserRole>(0);

	@Transient
	public String getCredentialsSalt() {
        return username + salt;
    }
	
	
	public SysUser() {
	}

	
	public SysUser(String username, String password, String salt) {
		super();
		this.username = username;
		this.password = password;
		this.salt = salt;
	}


	public SysUser(String name, String username, String password, String salt, boolean locked, String mobile,
			Date createtime) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.locked = locked;
		this.mobile = mobile;
		this.createtime = createtime;
	}

	public SysUser(String name, String username, String password, String salt, boolean locked, String mobile,
			Date createtime, Set<SysUserRole> sysUserRoles) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.locked = locked;
		this.mobile = mobile;
		this.createtime = createtime;
		this.sysUserRoles = sysUserRoles;
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

	@Column(name = "username", unique = true, nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "salt", nullable = false, length = 32)
	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "locked", nullable = false)
	public boolean isLocked() {
		return this.locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	@Column(name = "mobile", unique = true, nullable = false, length = 11)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysUser")
	public Set<SysUserRole> getSysUserRoles() {
		return this.sysUserRoles;
	}

	public void setSysUserRoles(Set<SysUserRole> sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}

}
