package com.jinmark.sys.service.permission;

import java.util.Set;

import com.jinmark.sys.domain.SysUser;
import com.jinmark.sys.vo.Response;


public interface SysUserServiceI {
	/**
	 * 修改密码
	 * @Description: TODO
	 * @param in
	 * @return SysUserOut  
	 * @throws
	 * @author qinchuan
	 * @date 2016-9-13 下午3:11:30
	 */
	//public SysUserOut changePassword(SysUserIn in);
	/**
	 * 根据用户名查找用户
	 * @Description: TODO 
	 * @param username
	 * @return
	 * @return SysUser  
	 * @throws
	 * @author qinchuan
	 * @date 2016-3-11 下午3:49:32
	 */
	public SysUser findByUsername(String username);
	/**
	 * 根据用户名查找其角色
	 * @param username
	 * @return Set
	 */
	public Set<String> findRoles(String username);
	/**
	 * 根据用户名查找其权限
	 * @param username
	 * @return Set
	 */
	public Set<String> findPermissions(String username);
	/**
	 * 保存新增用户
	 * @param username
	 * @param password
	 * @return Response
	 */
	public Response saveUser(String username, String password);
	/**
	 * 根据不同条件查询用户列表
	 * @Description: TODO 
	 * @param in
	 * @param pages
	 * @return
	 * @return SysUserOut  
	 * @throws
	 * @author qinchuan
	 * @date 2016-3-11 下午3:58:59
	 */
	//public SysUserOut queryUsers(SysUserIn in, Pages pages);
	/**
	 * 获取单个用户
	 * @Description: TODO 
	 * @param in
	 * @return
	 * @return SysUserOut  
	 * @throws
	 * @author qinchuan
	 * @date 2016-3-11 下午3:59:26
	 */
	//public SysUserOut getUser(SysUserIn in);
	/**
	 * 修改用户
	 * @Description: TODO 
	 * @param in
	 * @return
	 * @return SysUserOut  
	 * @throws
	 * @author qinchuan
	 * @date 2016-3-11 下午3:59:44
	 */
	//public SysUserOut editUser(SysUserIn in);
	/**
	 * 批量删除用户
	 * @Description: TODO 
	 * @param in
	 * @return
	 * @return SysUserOut  
	 * @throws
	 * @author qinchuan
	 * @date 2016-3-11 下午4:59:19
	 */
	//public SysUserOut deleteUsers(SysUserIn in);
	/**
	 * 添加用户页面
	 * @Description: TODO
	 * @return
	 * @return SysUserOut  
	 * @throws
	 * @author qinchuan
	 * @date 2016-9-8 下午1:55:04
	 */
	//public SysUserOut addUserPage();
	/**
	 * 编辑用户页面
	 * @Description: TODO
	 * @param in
	 * @return
	 * @return SysUserOut  
	 * @throws
	 * @author qinchuan
	 * @date 2016-9-8 下午2:33:54
	 */
	//public SysUserOut editUserPage(SysUserIn in);
}
