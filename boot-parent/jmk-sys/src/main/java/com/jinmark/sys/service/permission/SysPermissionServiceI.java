package com.jinmark.sys.service.permission;

import java.util.List;
import java.util.Set;

import com.jinmark.sys.domain.SysPermission;



public interface SysPermissionServiceI {
	/**
	 * 根据权限ID获取权限
	 * @param id
	 * @return SysPermission
	 */
	public SysPermission findOne(String id);
	/**
	 * 获取所有根权限
	 * @return List
	 */
	//public List<SysPermission> findAllRoot();
	
	/**
	 * 获取所有的权限菜单
	 * @return List
	 */
	public List<SysPermission> findAllMenus();
	/**
	 * 得到资源对应的权限字符串
	 * @param menuIds
	 * @return Set
	 */
	public Set<String> findPermissions(Set<String> menuIds);
	/**
	 * 根据用户权限得到菜单
	 * @param permissions
	 * @return List
	 */
	public List<SysPermission> findMenus(String userId);
}
