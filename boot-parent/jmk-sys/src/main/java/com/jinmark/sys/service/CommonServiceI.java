package com.jinmark.sys.service;

import com.jinmark.core.bean.Response;

/**
 * 
 * @author QC
 * @ClassName CommonServiceI
 * @Description TODO(公共的service接口) 
 * @date 2017年10月31日上午11:58:26
 */
public interface CommonServiceI {
	/**
	 * 
	 * @Title validateMobile
	 * @Description TODO(验证电话号码唯一性) 
	 * @param userId
	 * @param mobile
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response validateMobile(String userId, String mobile);

	/**
	 * 
	 * @Title validateUsername
	 * @Description TODO(验证账号是否唯一) 
	 * @param userId
	 * @param username
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response validateUsername(String userId, String username);
	/**
	 * 
	 * @Title validateRoleName
	 * @Description TODO(验证角色名称唯一性) 
	 * @param roleId
	 * @param roleName
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response validateRoleName(String roleId, String roleName);
	/**
	 * 
	 * @Title validateRoleFlag
	 * @Description TODO(验证角色标识唯一性) 
	 * @param roleId
	 * @param roleFlag
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	Response validateRoleFlag(String roleId, String roleFlag);

}
