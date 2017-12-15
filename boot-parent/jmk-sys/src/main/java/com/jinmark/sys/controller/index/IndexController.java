package com.jinmark.sys.controller.index;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinmark.core.Constants;
import com.jinmark.sys.domain.SysPermission;
import com.jinmark.sys.domain.SysUser;
import com.jinmark.sys.service.permission.SysPermissionServiceI;

/**
 * 
 * @author QC
 * @ClassName IndexController
 * @Description TODO(这里用一句话描述这个类的作用) 
 * @date 2017年8月31日下午3:56:01
 */
@Controller
public class IndexController {
	
	@Autowired
	private SysPermissionServiceI permissionService;
	
	/**
	 * 
	 * @Title indexPage
	 * @Description TODO(首页) 
	 * @param model
	 * @return
	 * @throws Exception
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/")
	public String indexPage(Model model, HttpSession session) {
		List<SysPermission> menus = permissionService.findUserMenus(((SysUser)session.getAttribute(Constants.CURRENT_USER)).getId());
		model.addAttribute("menus", menus);
		return "index";
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @Title home
	 * @Description TODO(主页) 
	 * @return
	 * @throws Exception
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	
}
