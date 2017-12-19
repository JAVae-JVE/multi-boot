package com.jinmark.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jinmark.sys.domain.SysUserRole;
import com.jinmark.sys.vo.permission.UserRequest;

public interface SysUserRoleRepository extends JpaRepository<SysUserRole, String> {
	@Modifying 
	@Query("update SysUserRole o set o.sysRole.id = :#{#userRequest.roleId} where o.sysUser.id = :#{#userRequest.id}") 
	void updateSysUserRoler(@Param("userRequest") UserRequest userRequest);
}
