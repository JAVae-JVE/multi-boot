package com.jinmark.sys.controller.permission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author QC
 * @ClassName RoleController
 * @Description TODO(角色管理controller) 
 * @date 2017年9月14日下午5:39:52
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	/**
	 * 
	 * @Title roleList
	 * @Description TODO(角色列表页面) 
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/list")
	public String roleList(Model model) {
		return "permission/role/role_view";
	}
	
	/**
	 * 
	 * @Title roleAddOrEdit
	 * @Description TODO(新增或编辑角色页面) 
	 * @param roleId
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/add_or_edit")
	public String roleAddOrEdit(String roleId, Model model) {
		return "permission/role/role_form";
	}
	
	/**
	 * 
	 * @Title grantPage
	 * @Description TODO(授权页面) 
	 * @param roleId
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/grant_page")
	public String grantPage(String roleId, Model model) {
		return "permission/role/role_grant";
	}
}
