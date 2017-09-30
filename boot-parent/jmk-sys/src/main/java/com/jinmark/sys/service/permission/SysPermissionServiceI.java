package com.jinmark.sys.service.permission;

import java.util.List;
import java.util.Set;

import com.jinmark.core.bean.Response;
import com.jinmark.sys.domain.SysPermission;



public interface SysPermissionServiceI {
	
	/**
	 * 得到资源对应的权限字符串
	 * @param menuIds
	 * @return Set
	 */
	Set<String> findPermissions(Set<String> menuIds);
	/**
	 * 
	 * @Title findSysPermissions
	 * @Description TODO(递归获取所有菜单) 
	 * @return
	 * @return List<SysPermission>  返回类型 
	 * @throws
	 */
	List<SysPermission> findSysPermissions();
	/**
	 * 
	 * @Title findParent
	 * @Description TODO(根据菜单类型获取父级菜单) 
	 * @param menuType
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response findParent(String menuType);
}
