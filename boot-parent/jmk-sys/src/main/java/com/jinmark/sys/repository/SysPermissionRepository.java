package com.jinmark.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
	 * @Title findSecondLevelMenu
	 * @Description TODO(获取二级菜单) 
	 * @param available
	 * @param resourceType
	 * @return
	 * @return List<SysPermission>  返回类型 
	 * @throws
	 */
	@Query("SELECT * FROM SysPermission o WHERE o.available = :available AND o.resourceType = :resourceType AND o.parentId != '' AND o.parentId is not null ORDER BY o.priority ASC")
	List<SysPermission> findSecondLevelMenu(@Param("available")boolean available, @Param("resourceType")String resourceType);
	
}
