package com.jinmark.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jinmark.sys.domain.SysRolePermission;

public interface SysRolePermissionRepository extends JpaRepository<SysRolePermission, String> {
	
	@Modifying
	@Query("delete from SysRolePermission o where o.sysPermission.id = :permId")
	void deleteByPermId(@Param("permId") String permId);
}
