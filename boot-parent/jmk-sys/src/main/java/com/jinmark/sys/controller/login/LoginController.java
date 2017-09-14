package com.jinmark.sys.controller.login;


import javax.validation.Valid;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.jinmark.core.bean.Response;
import com.jinmark.sys.service.login.LoginServiceI;
import com.jinmark.sys.vo.LoginRequest;

@Controller
public class LoginController {
	
	
	@Autowired
	private LoginServiceI loginService;
	
	@ModelAttribute  
    public void populateModel(LoginRequest req, Model model) {  
		model.addAttribute("req", req); 
    } 
	
	/**
	 * 
	 * @Title loginPage
	 * @Description TODO(登录页面) 
	 * @return
	 * @throws Exception
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/login_page")
	public String loginPage() {
		return "login";
	}
	
	/**
	 * 
	 * @Title login
	 * @Description TODO(登录) 
	 * @param req
	 * @param result
	 * @param model
	 * @throws Exception
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/login")
	public String login(@Valid LoginRequest req, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "login";
		}else {
			String resultPageURL = "";
			Response res = loginService.login(req.getUsername(), req.getPassword());
			if(res.isSuccess()) {
				Subject currentUser = SecurityUtils.getSubject();
				if(currentUser.isAuthenticated()) {
					resultPageURL += InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
				}else {
					resultPageURL += InternalResourceViewResolver.FORWARD_URL_PREFIX + "/login_page";
				}
				
			}else {
				resultPageURL += InternalResourceViewResolver.FORWARD_URL_PREFIX + "/login_page";
			}
		    return resultPageURL;
		}
		
		
	}
	
}
