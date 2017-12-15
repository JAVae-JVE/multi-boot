package com.jinmark.sys.controller.permission;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinmark.core.bean.Pages;
import com.jinmark.core.bean.Response;
import com.jinmark.sys.service.permission.SysRoleServiceI;
import com.jinmark.sys.service.permission.SysUserServiceI;
import com.jinmark.sys.vo.permission.QueryUserRequest;
import com.jinmark.sys.vo.permission.UserRequest;
/**
 * 
 * @author QC
 * @ClassName UserController
 * @Description TODO(用户管理controller) 
 * @date 2017年9月14日下午5:41:11
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private SysUserServiceI userService;
	@Autowired
	private SysRoleServiceI roleService;
	
	/**
	 * 
	 * @Title userList
	 * @Description TODO(用户列表页面) 
	 * @param queryUserRequest 查询条件
	 * @param pages 分页对象
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/list")
	public String userList(QueryUserRequest queryUserRequest, Pages pages, Model model) {
		model.addAttribute("userList", userService.queryUserList(queryUserRequest, pages));
		return "permission/user/user_view";
	}
	
	/**
	 * 
	 * @Title userEdit
	 * @Description TODO(新增用户页面) 
	 * @param userId
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/add")
	public String userAdd(Model model) {
		model.addAttribute("roles", roleService.findRoles());
		return "permission/user/user_form";
	}
	
	/**
	 * 
	 * @Title useredit
	 * @Description TODO(编辑用户页面) 
	 * @param id
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/edit_{id}")
	public String userEdit(@PathVariable("id") String id, Model model) {
		model.addAttribute("roles", roleService.findRoles());
		return "permission/user/user_form";
	}
	
	/**
	 * 
	 * @Title userCreate
	 * @Description TODO(新增用户) 
	 * @param userRequest
	 * @param result
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/create")
	public Response userCreate(@Valid UserRequest userRequest, BindingResult result) {
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
	 * 编辑用户
	 * @Title userUpdate
	 * @Description TODO(这里用一句话描述这个方法的作用) 
	 * @param userRequest
	 * @param result
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/update")
	public Response userUpdate(@Valid UserRequest userRequest, BindingResult result) {
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
	 * @Title pswdSetting
	 * @Description TODO(密码设置页面) 
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/pswd_setting")
	public String pswdSetting() {
		return "permission/user/pswd_setting";
	}
}
