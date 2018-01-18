package com.jinmark.sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jinmark.sys.domain.SysUserRole;
import com.jinmark.sys.vo.permission.UserRequest;

public interface SysUserRoleRepository extends JpaRepository<SysUserRole, String> {
	/**
	 * 
	 * @Title updateSysUserRole
	 * @Description TODO(修改用户的角色) 
	 * @param userRequest
	 * @return void  返回类型 
	 * @throws
	 */
	@Modifying 
	@Query("update SysUserRole o set o.sysRole.id = :#{#userRequest.roleId} where o.sysUser.id = :#{#userRequest.id}") 
	void updateSysUserRole(@Param("userRequest") UserRequest userRequest);
	
	/**
	 * 
	 * @Title deleteSysUserRole
	 * @Description TODO(删除用户的角色) 
	 * @param userId
	 * @return void  返回类型 
	 * @throws
	 */
	@Modifying 
	@Query("delete from SysUserRole o where o.sysUser.id in(:userIds)") 
	void deleteSysUserRole(@Param("userIds") String userIds);
	
	/**
	 * 
	 * @Title deleteSysUserRoleByRoleId
	 * @Description TODO(根据角色id删除用户-角色) 
	 * @param roleId
	 * @return void  返回类型 
	 * @throws
	 */
	@Modifying 
	@Query("delete from SysUserRole o where o.sysRole.id = :roleId") 
	void deleteSysUserRoleByRoleId(@Param("roleId") String roleId);
}
