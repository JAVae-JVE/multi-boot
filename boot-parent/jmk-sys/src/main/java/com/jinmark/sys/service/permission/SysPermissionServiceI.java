package com.jinmark.sys.service.permission;

import java.util.List;
import java.util.Set;

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
}
