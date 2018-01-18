package com.jinmark.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jinmark.sys.domain.SysRole;

public interface SysRoleRepository extends JpaRepository<SysRole, String> {
	/**
	 * 根据available获取角色
	 * @param available
	 * @return
	 * @return List<SysRole>
	 */
	List<SysRole> findByAvailable(boolean available);
	/**
	 * 
	 * @Title findByRoleName
	 * @Description TODO(根据角色名称获取角色) 
	 * @param roleName
	 * @return
	 * @return SysRole  返回类型 
	 * @throws
	 */
	SysRole findByRoleName(String roleName);
	/**
	 * 
	 * @Title findByRoleFlag
	 * @Description TODO(根据角色标识获取角色) 
	 * @param roleFlag
	 * @return
	 * @return SysRole  返回类型 
	 * @throws
	 */
	SysRole findByRoleFlag(String roleFlag);
	/**
	 * 
	 * @Title updateRole
	 * @Description TODO(修改角色) 
	 * @param role
	 * @return void  返回类型 
	 * @throws
	 */
	@Modifying 
	@Query("update SysRole o set o.roleName = :#{#role.roleName}, o.description = :#{#role.description} where o.id = :#{#role.id}") 
	void updateRole(@Param("role") SysRole role);
	
	/**
	 * 
	 * @Title getById
	 * @Description TODO(根据id获取对象) 
	 * @param id
	 * @return
	 * @return SysRole  返回类型 
	 * @throws
	 */
	SysRole getById(String id);
	
}
