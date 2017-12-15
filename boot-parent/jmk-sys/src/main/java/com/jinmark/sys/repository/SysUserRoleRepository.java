package com.jinmark.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinmark.sys.domain.SysUserRole;

public interface SysUserRoleRepository extends JpaRepository<SysUserRole, String> {
	
}
