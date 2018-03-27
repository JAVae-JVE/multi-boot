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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinmark.core.bean.Response;
import com.jinmark.sys.domain.SysRole;
import com.jinmark.sys.service.permission.SysPermissionServiceI;
import com.jinmark.sys.service.permission.SysRoleServiceI;
import com.jinmark.sys.vo.permission.RoleRequest;

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
	
	@Autowired
	private SysRoleServiceI roleService;
	
	@Autowired
	private SysPermissionServiceI permissionService;
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
		model.addAttribute("roleList", roleService.findRoleList());
		return "permission/role/role_view";
	}
	
	/**
	 * 
	 * @Title roleAdd
	 * @Description TODO(新增角色页面) 
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/add")
	public String roleAdd(Model model) {
		return "permission/role/role_form";
	}
	
	/**
	 * 
	 * @Title roleEdit
	 * @Description TODO(编辑角色页面) 
	 * @param roleId
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/edit_{id}")
	public String roleEdit(@PathVariable String id, Model model) {
		model.addAttribute("roleId", id);
		return "permission/role/role_form";
	}
	
	/**
	 * 
	 * @Title roleGet
	 * @Description TODO(获取单个角色) 
	 * @param id
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/get_{id}")
	@ResponseBody
	public Response roleGet(@PathVariable("id") String id) {
		return roleService.getRole(id);
	}
	
	/**
	 * 
	 * @Title roleCreate
	 * @Description TODO(新增角色) 
	 * @param roleRequest
	 * @param result
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/create")
	@ResponseBody
	public Response roleCreate(@Valid RoleRequest roleRequest, BindingResult result) {
		Response res = new Response("");
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();  
            for (ObjectError error : list) { 
            	res.setMsg(res.getMsg() + "[" + error.getDefaultMessage() + "]");
            }  
		}else {
			SysRole role = new SysRole(null, roleRequest.getRoleName(), roleRequest.getRoleFlag(), roleRequest.getDescription());
			res = roleService.saveRole(role);
		}
		
		return res;
	}
	
	/**
	 * 
	 * @Title roleUpdate
	 * @Description TODO(更新角色) 
	 * @param roleRequest
	 * @param result
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Response roleUpdate(@Valid RoleRequest roleRequest, BindingResult result) {
		Response res = new Response("");
		if(StringUtils.isBlank(roleRequest.getId())) {
			res.setMsg("未传入修改对象的唯一标识");
		} else if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();  
            for (ObjectError error : list) { 
            	res.setMsg(res.getMsg() + "[" + error.getDefaultMessage() + "]");
            }  
		}else {
			SysRole role = new SysRole(roleRequest.getId(), roleRequest.getRoleName(), roleRequest.getRoleFlag(), roleRequest.getDescription());
			res = roleService.saveRole(role);
		}
		
		return res;
	}
	
	
	/**
	 * 
	 * @Title permDelete
	 * @Description TODO(删除角色) 
	 * @param id
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/delete_{id}")
	@ResponseBody
	public Response roleDelete(@PathVariable("id") String id) {
		return roleService.deleteRole(id);
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
	@RequestMapping("/grant_page_{roleId}_{roleName}")
	public String grantPage(@PathVariable("roleId") String roleId, @PathVariable("roleName") String roleName, Model model) {
		model.addAttribute("roleId", roleId);
		model.addAttribute("roleName", roleName);
		model.addAttribute("menus", permissionService.findSysPermissionsByRoleId(roleId));
		return "permission/role/role_grant";
	}
	
	/**
	 * 
	 * @Title roleGrant
	 * @Description TODO(为角色授权) 
	 * @param roleId
	 * @param perm
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/grant")
	@ResponseBody
	public Response roleGrant(String roleId, @RequestParam("perm[]") List<String> perm) {
		return roleService.roleGrant(roleId, perm);
	}
}
