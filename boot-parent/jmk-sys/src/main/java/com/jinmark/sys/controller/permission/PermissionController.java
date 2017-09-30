package com.jinmark.sys.controller.permission;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinmark.core.Constants.ResType;
import com.jinmark.core.bean.Response;
import com.jinmark.core.bean.Selected;
import com.jinmark.sys.exception.JSONException;
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
	 * @Title permAdd
	 * @Description TODO(新增菜单页面) 
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/add")
	public String permAdd(Model model) {
		List<Selected> items = new ArrayList<Selected>();
		items.add(new Selected(ResType.MENU.getValue(), ResType.MENU.getName(), false));
		items.add(new Selected(ResType.BUTTON.getValue(), ResType.BUTTON.getName(), false));
		model.addAttribute("menu_type", items);
		return "permission/perm/perm_form";
	}
	
	/**
	 * 
	 * @Title permEdit
	 * @Description TODO(编辑菜单页面) 
	 * @param id
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/edit_{id}")
	public String permEdit(@PathVariable("id") String id, Model model) {
		
		return "permission/perm/perm_form";
	}
	
	/**
	 * 
	 * @Title permFindParent
	 * @Description TODO(根据菜单类型获取父级菜单) 
	 * @param menuType
	 * @return
	 * @throws JSONException
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/parent_{type}")
	@ResponseBody
	public Response permFindParent(@PathVariable("type") String menuType) throws JSONException {
		return permissionService.findParent(menuType);
	}
	
	/**
	 * 
	 * @Title permCreate
	 * @Description TODO(新增菜单表单提交) 
	 * @return
	 * @throws JSONException
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/create")
	public Response permCreate() throws JSONException {
		return null;
	}
	
	/**
	 * 
	 * @Title permUpdate
	 * @Description TODO(编辑菜单表单提交) 
	 * @return
	 * @throws JSONException
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/update")
	public Response permUpdate() throws JSONException {
		return null;
	}
}
