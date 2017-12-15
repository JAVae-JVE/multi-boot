package com.jinmark.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.jinmark.sys.domain.SysPermission;

public interface SysPermissionRepository extends JpaRepository<SysPermission, String> {
	
	/**
	 * 
	 * @Title findByAvailableAndParentIdIsNullOrderByPriorityAsc
	 * @Description TODO(获取所有根菜单) 
	 * @param available
	 * @return
	 * @return List<SysPermission>  返回类型 
	 * @throws
	 */
	List<SysPermission> findByAvailableAndParentIdIsNullOrderByPriorityAsc(boolean available);
	/**
	 * 
	 * @Title findByAvailableAndParentIdOrderByPriorityAsc
	 * @Description TODO(获取子菜单集合) 
	 * @param available
	 * @param parentId
	 * @return
	 * @return List<SysPermission>  返回类型 
	 * @throws
	 */
	List<SysPermission> findByAvailableAndParentIdOrderByPriorityAsc(boolean available, String parentId);
	
	/**
	 * 
	 * @Title updateSysPermission
	 * @Description TODO(更新菜单) 
	 * @param sysPermission
	 * @return void  返回类型 
	 * @throws
	 */
	@Modifying 
	@Query("update SysPermission o set o.name = :#{#sysPermission.name}, o.resourceType = :#{#sysPermission.resourceType}, o.url = :#{#sysPermission.url}, o.permission = :#{#sysPermission.permission}, o.parentId = :#{#sysPermission.parentId}, o.priority = :#{#sysPermission.priority}, o.iconClass = :#{#sysPermission.iconClass} where o.id = :#{#sysPermission.id}") 
	void updateSysPermission(@Param("sysPermission") SysPermission sysPermission);
	
	/**
	 * 
	 * @Title findUserParentMenus
	 * @Description TODO(获取当前用户能够访问的跟菜单) 
	 * @param userId
	 * @return
	 * @return List<SysPermission>  返回类型 
	 * @throws
	 */
	@Query(value = "SELECT p.* FROM sys_user_role u_r LEFT JOIN sys_role_permission r_p ON u_r.role_id = r_p.role_id LEFT JOIN sys_permission p ON r_p.permission_id = p.id WHERE u_r.user_id = ?1 AND (p.parent_id = '' OR p.parent_id IS NULL) ORDER BY p.priority", nativeQuery = true)
	List<SysPermission> findUserParentMenus(String userId);
	
	/**
	 * 
	 * @Title findUserChildrenMenus
	 * @Description TODO(获取当前用户能够访问的子孙菜单) 
	 * @param userId
	 * @param parentId
	 * @return
	 * @return List<SysPermission>  返回类型 
	 * @throws
	 */
	@Query(value = "SELECT p.* FROM sys_user_role u_r LEFT JOIN sys_role_permission r_p ON u_r.role_id = r_p.role_id LEFT JOIN sys_permission p ON r_p.permission_id = p.id WHERE u_r.user_id = ?1 AND p.parent_id = ?2 AND p.resource_type = 'menu' ORDER BY p.priority", nativeQuery = true)
	List<SysPermission> findUserChildrenMenus(String userId, String parentId);
	
	/**
	 * 
	 * @Title getById
	 * @Description TODO(根据id获取菜单) 
	 * @param id
	 * @return
	 * @return SysPermission  返回类型 
	 * @throws
	 */
	SysPermission getById(String id);
}
