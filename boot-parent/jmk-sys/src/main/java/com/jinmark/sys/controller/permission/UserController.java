package com.jinmark.sys.controller.permission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
