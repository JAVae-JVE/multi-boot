package com.jinmark.sys.service.permission;

import java.util.List;
import java.util.Set;

import com.jinmark.sys.domain.SysRole;


public interface SysRoleServiceI {
	/**
	 * 根据角色ID获取角色
	 * @param roleId
	 * @return SysRole
	 */
	public SysRole findOne(String roleId);
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
	 * 获取所有启用的角色
	 * @return
	 * @return List<SysRole>
	 */
	public List<SysRole> findAllRoles();
	/**
	 * 查询角色
	 * @Description: TODO
	 * @param in
	 * @param pages
	 * @return
	 * @return SysRoleOut  
	 * @throws
	 * @author qinchuan
	 * @date 2016-9-8 下午3:29:33
	 */
	/*public SysRoleOut queryRoles(SysRoleIn in, Pages pages);
	*//**
	 * 添加角色页面
	 * @Description: TODO
	 * @return
	 * @return SysRoleOut  
	 * @throws
	 * @author qinchuan
	 * @date 2016-9-8 下午3:32:29
	 *//*
	public SysRoleOut addRolePage();
	*//**
	 * 保存角色
	 * @Description: TODO
	 * @param in
	 * @return
	 * @return SysRoleOut  
	 * @throws
	 * @author qinchuan
	 * @date 2016-9-8 下午3:35:09
	 *//*
	public SysRoleOut saveRole(SysRoleIn in);
	*//**
	 * 编辑角色页面
	 * @Description: TODO
	 * @param in
	 * @return
	 * @return SysRoleOut  
	 * @throws
	 * @author qinchuan
	 * @date 2016-9-8 下午3:37:34
	 *//*
	public SysRoleOut editRolePage(SysRoleIn in);
	*//**
	 * 编辑角色
	 * @Description: TODO
	 * @param in
	 * @return
	 * @return SysRoleOut  
	 * @throws
	 * @author qinchuan
	 * @date 2016-9-8 下午3:38:57
	 *//*
	public SysRoleOut editRole(SysRoleIn in);
	*//**
	 * 删除角色
	 * @Description: TODO
	 * @param in
	 * @return
	 * @return SysRoleOut  
	 * @throws
	 * @author qinchuan
	 * @date 2016-9-8 下午3:41:38
	 *//*
	public SysRoleOut deleteRoles(SysRoleIn in);*/
}
