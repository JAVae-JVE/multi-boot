package com.jinmark.sys.controller.permission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinmark.core.bean.Response;
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
	/**
	 * 
	 * @Title userList
	 * @Description TODO(用户列表页面) 
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/list")
	public String userList(Model model) {
		return "permission/user/user_view";
	}
	
	/**
	 * 
	 * @Title userAddOrEdit
	 * @Description TODO(新增或编辑用户页面) 
	 * @param model
	 * @param userId
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/add_or_edit")
	public String userAddOrEdit(String userId, Model model) {
		return "permission/user/user_form";
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
