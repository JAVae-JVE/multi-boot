package com.jinmark.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinmark.core.bean.Response;
import com.jinmark.sys.service.CommonServiceI;

/**
 * 
 * @author QC
 * @ClassName CommonController
 * @Description TODO(公共controller) 
 * @date 2017年10月31日上午11:50:38
 */
@Controller
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * 
	 * @Title validateMobile
	 * @Description TODO(验证电话号码唯一性) 
	 * @param mobile
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/v_m")
	@ResponseBody
	public Response validateMobile(String userId, String mobile) {
		return commonService.validateMobile(userId, mobile);
	}
	
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
	@RequestMapping("/v_u")
	@ResponseBody
	public Response validateUsername(String userId, String username) {
		return commonService.validateUsername(userId, username);
	}
	
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
	@RequestMapping("/v_role_name")
	@ResponseBody
	public Response validateRoleName(String roleId, String roleName) {
		return commonService.validateRoleName(roleId, roleName);
	}
	
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
	@RequestMapping("/v_role_flag")
	@ResponseBody
	public Response validateRoleFlag(String roleId, String roleFlag) {
		return commonService.validateRoleFlag(roleId, roleFlag);
	}
}
