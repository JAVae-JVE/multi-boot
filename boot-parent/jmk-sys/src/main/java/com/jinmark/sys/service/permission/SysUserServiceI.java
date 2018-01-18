package com.jinmark.sys.service.permission;

import java.util.List;
import java.util.Set;

import com.jinmark.core.bean.Pages;
import com.jinmark.core.bean.Response;
import com.jinmark.sys.domain.SysUser;
import com.jinmark.sys.vo.permission.QueryUserRequest;
import com.jinmark.sys.vo.permission.UserRequest;


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
	 * @Title saveSysUser
	 * @Description TODO(保存用户) 
	 * @param userRequest
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response saveSysUser(UserRequest userRequest);
	
	/**
	 * 
	 * @Title getSysUser
	 * @Description TODO(根据用户id获取用户) 
	 * @param id
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response getSysUser(String id);
	
	/**
	 * 
	 * @Title deleteSysUser
	 * @Description TODO(删除用户) 
	 * @param ids
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response deleteSysUser(List<String> ids);
}
