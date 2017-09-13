package com.jinmark.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinmark.sys.domain.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, String> {
	/**
	 * 通过用户名获取用户
	 * @param username
	 * @return SysUser
	 */
	SysUser findByUsername(String username);
}
