package com.jinmark.sys.controller.permission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
		return "permission/perm/perm_view";
	}
}
