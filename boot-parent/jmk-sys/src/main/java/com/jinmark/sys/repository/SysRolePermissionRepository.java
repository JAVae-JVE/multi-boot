package com.jinmark.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jinmark.sys.domain.SysRolePermission;

public interface SysRolePermissionRepository extends JpaRepository<SysRolePermission, String> {
	/**
	 * 
	 * @Title deleteByPermId
	 * @Description TODO(根据权限id删除角色-权限) 
	 * @param permId
	 * @return void  返回类型 
	 * @throws
	 */
	@Modifying
	@Query("delete from SysRolePermission o where o.sysPermission.id = :permId")
	void deleteByPermId(@Param("permId") String permId);
	
	/**
	 * 
	 * @Title deleteByRoleId
	 * @Description TODO(根据角色id删除角色-权限) 
	 * @param roleId
	 * @return void  返回类型 
	 * @throws
	 */
	@Modifying
	@Query("delete from SysRolePermission o where o.sysRole.id = :roleId")
	void deleteByRoleId(@Param("roleId") String roleId);
}
