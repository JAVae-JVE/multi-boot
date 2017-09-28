package com.jinmark.sys.controller.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinmark.sys.service.permission.SysPermissionServiceI;
/**
 * 
 * @author QC
 * @ClassName PermissionController
 * @Description TODO(菜单管理controller) 
 * @date 2017年9月13日上午9:18:03
 */
@Controller
@RequestMapping("/perm")
public class PermissionController {
	
	@Autowired
	private SysPermissionServiceI permissionService;
	/**
	 * 
	 * @Title permList
	 * @Description TODO(菜单列表页面) 
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/list")
	public String permList(Model model) {
		model.addAttribute("menus", permissionService.findSysPermissions());
		return "permission/perm/perm_view";
	}
	
	/**
	 * 
	 * @Title permAddOrEdit
	 * @Description TODO(新增或编辑菜单页面) 
	 * @param permId
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/add_or_edit")
	public String permAddOrEdit(String permId, Model model) {
		return "permission/perm/perm_form";
	}
}
