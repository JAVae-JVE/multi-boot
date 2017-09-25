package com.jinmark.sys.controller.cases;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author QC
 * @ClassName CaseController
 * @Description TODO(案例controller) 
 * @date 2017年9月25日下午5:08:45
 */
@Controller
@RequestMapping("/case")
public class CaseController {
	
	/**
	 * 
	 * @Title caseList
	 * @Description TODO(我的案例列表) 
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/list")
	public String caseList(Model model) {
		return "case/case_view";
	}
	
	/**
	 * 
	 * @Title caseListByUser
	 * @Description TODO(某个用户的案例列表：超级管理员用) 
	 * @param userId
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/list_by_user")
	public String caseListByUser(String userId, Model model) {
		return "case/case_view";
	}
	
	/**
	 * 
	 * @Title caseAddOrEdit
	 * @Description TODO(新增或者编辑案例) 
	 * @param caseId
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/add_or_edit")
	public String caseAddOrEdit(String caseId, Model model) {
		return "case/case_form";
	}
	
}
