package com.jinmark.sys.service.permission;

import java.util.List;
import java.util.Set;

import com.jinmark.core.bean.Selected;


public interface SysRoleServiceI {
	/**
	 * 根据角色编号得到角色标识符列表
	 * @param roleIds
	 * @return Set<String>
	 */
	Set<String> findRoles(String... roleIds);
	/**
	 * 根据角色编号得到权限字符串列表
	 * @param roleIds
	 * @return Set<String>
	 */
	Set<String> findPerssions(String[] roleIds);
	/**
	 * 
	 * @Title findRoles
	 * @Description TODO(获取角色) 
	 * @return
	 * @return List<Selected>  返回类型 
	 * @throws
	 */
	List<Selected> findRoles();
}
