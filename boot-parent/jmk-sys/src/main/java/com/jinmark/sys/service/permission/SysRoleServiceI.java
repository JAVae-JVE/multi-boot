package com.jinmark.sys.service.permission;

import java.util.List;
import java.util.Set;

import com.jinmark.core.bean.Response;
import com.jinmark.core.bean.Selected;
import com.jinmark.sys.domain.SysRole;


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
	/**
	 * 
	 * @Title findRoleList
	 * @Description TODO(获取角色列表) 
	 * @return
	 * @return List<SysRole>  返回类型 
	 * @throws
	 */
	List<SysRole> findRoleList();
	/**
	 * 
	 * @Title saveRole
	 * @Description TODO(保存角色) 
	 * @param role
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response saveRole(SysRole role);
	/**
	 * 
	 * @Title getRole
	 * @Description TODO(根据角色id获取角色对象) 
	 * @param id
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response getRole(String id);
	/**
	 * 
	 * @Title deleteRole
	 * @Description TODO(删除角色) 
	 * @param id
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response deleteRole(String id);
	/**
	 * 
	 * @Title roleGrant
	 * @Description TODO(为角色授权) 
	 * @param roleId
	 * @param perm
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response roleGrant(String roleId, List<String> perm);
}
