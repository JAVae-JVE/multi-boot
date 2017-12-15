package com.jinmark.sys.controller.permission;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinmark.core.bean.Response;
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
	@RequestMapping("/edit_{roleId}")
	public String roleEdit(@PathVariable String roleId, Model model) {
		return "permission/role/role_form";
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
	public Response roleCreate(@Valid RoleRequest roleRequest, BindingResult result) {
		Response res = new Response("");
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();  
            for (ObjectError error : list) { 
            	res.setMsg(res.getMsg() + "[" + error.getDefaultMessage() + "]");
            }  
		}else {
			
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
	public Response roleUpdate(@Valid RoleRequest roleRequest, BindingResult result) {
		Response res = new Response("");
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();  
            for (ObjectError error : list) { 
            	res.setMsg(res.getMsg() + "[" + error.getDefaultMessage() + "]");
            }  
		}else {
			
		}
		
		return res;
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
