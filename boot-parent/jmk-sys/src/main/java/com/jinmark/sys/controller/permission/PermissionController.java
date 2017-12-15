package com.jinmark.sys.controller.permission;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jinmark.core.bean.Response;
import com.jinmark.sys.domain.SysPermission;
import com.jinmark.sys.service.permission.SysPermissionServiceI;
import com.jinmark.sys.vo.permission.MenuRequest;
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
		model.addAttribute("perm_id", id);
		return "permission/perm/perm_form";
	}
	
	
	/**
	 * 
	 * @Title permGet
	 * @Description TODO(根据id获取菜单对象) 
	 * @param id
	 * @return
	 * @throws JSONException
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/get_{id}")
	@ResponseBody
	public Response permGet(@PathVariable("id") String id) {
		return permissionService.getSysPermission(id);
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
	public Response permFindParent(@PathVariable("type") String menuType) {
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
	@ResponseBody
	public Response permCreate(@Valid MenuRequest menuRequest, BindingResult result) {
		Response res = new Response("");
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();  
            for (ObjectError error : list) { 
            	res.setMsg(res.getMsg() + "[" + error.getDefaultMessage() + "]");
            }  
		}else {
			SysPermission sysPermission = new SysPermission(null, menuRequest.getName(), menuRequest.getResourceType(), menuRequest.getUrl(), menuRequest.getPermission(), menuRequest.getParentId(), menuRequest.getPriority(), menuRequest.getIconClass());
			res = permissionService.saveSysPermission(sysPermission);
		}
		return res;
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
	@ResponseBody
	public Response permUpdate(@Valid MenuRequest menuRequest, BindingResult result) {
		Response res = new Response("");
		if(StringUtils.isBlank(menuRequest.getId())) {
			res.setMsg("未传入修改对象的唯一标识");
		} else if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();  
            for (ObjectError error : list) { 
            	res.setMsg(res.getMsg() + "[" + error.getDefaultMessage() + "]");
            }  
		}else {
			SysPermission sysPermission = new SysPermission(menuRequest.getId(), menuRequest.getName(), menuRequest.getResourceType(), menuRequest.getUrl(), menuRequest.getPermission(), menuRequest.getParentId(), menuRequest.getPriority(), menuRequest.getIconClass());
			res = permissionService.saveSysPermission(sysPermission);
		}
		return res;
	}
	
	/**
	 * 
	 * @Title permDelete
	 * @Description TODO(删除菜单) 
	 * @param id
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/delete_{id}")
	@ResponseBody
	public Response permDelete(@PathVariable("id") String id) {
		return permissionService.deleteSysPermission(id);
	}
}
