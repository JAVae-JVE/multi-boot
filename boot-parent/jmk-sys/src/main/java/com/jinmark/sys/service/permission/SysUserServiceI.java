package com.jinmark.sys.service.permission;

import java.util.List;
import java.util.Set;

import com.jinmark.core.bean.Pages;
import com.jinmark.sys.domain.SysUser;
import com.jinmark.sys.vo.permission.QueryUserRequest;


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
	SysUser findByUsername(String username);
	/**
	 * 根据用户名查找其角色
	 * @param username
	 * @return Set
	 */
	Set<String> findRoles(String username);
	/**
	 * 根据用户名查找其权限
	 * @param username
	 * @return Set
	 */
	Set<String> findPermissions(String username);
	/**
	 * 
	 * @Title queryUserList
	 * @Description TODO(用户列表) 
	 * @param queryUserRequest
	 * @param pages
	 * @return
	 * @return List<SysUser>  返回类型 
	 * @throws
	 */
	List<SysUser> queryUserList(QueryUserRequest queryUserRequest, Pages pages);
	/**
	 * 
	 * @Title getUserById
	 * @Description TODO(根据用户id获取用户) 
	 * @param userId
	 * @return
	 * @return SysUser  返回类型 
	 * @throws
	 */
	SysUser getUserById(String userId);
	/**
	 * 保存新增用户
	 * @param username
	 * @param password
	 * @return Response
	 */
	//public Response saveUser(String username, String password);
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
