package com.jinmark.sys.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jinmark.sys.domain.SysUser;
import com.jinmark.sys.vo.permission.QueryUserRequest;

public interface SysUserRepository extends JpaRepository<SysUser, String> {
	/**
	 * 通过用户名获取用户
	 * @param username
	 * @return SysUser
	 */
	SysUser findByUsername(String username);
	
	/**
	 * 
	 * @Title findUserList
	 * @Description TODO(多条件+分页查询用户列表) 
	 * @param queryUserRequest
	 * @param pageable
	 * @return
	 * @return List<SysUser>  返回类型 
	 * @throws
	 */
	@Query("SELECT o FROM SysUser o WHERE o.username LIKE :#{#queryUserRequest.name} OR o.name LIKE :#{#queryUserRequest.name} OR o.mobile LIKE :#{#queryUserRequest.name}")
	List<SysUser> findUserList(@Param("queryUserRequest") QueryUserRequest queryUserRequest, Pageable pageable);
	/**
	 * 
	 * @Title findByMobile
	 * @Description TODO(通过电话号码获取用户) 
	 * @param mobile
	 * @return
	 * @return SysUser  返回类型 
	 * @throws
	 */
	SysUser findByMobile(String mobile);
	
	@Modifying 
	@Query("update SysUser o set o.name = :#{#sysUser.name}, o.mobile = :#{#sysUser.mobile} where o.id = :#{#sysUser.id}") 
	void updateSysUser(@Param("sysUser") SysUser sysUser);
}
