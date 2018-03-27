package com.jinmark.sys.controller.system;

import java.util.Date;
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
import com.jinmark.sys.domain.Industry;
import com.jinmark.sys.service.system.IndustryServiceI;
import com.jinmark.sys.vo.system.IndusRequest;

/**
 * 
 * @author QC
 * @ClassName IndustryController
 * @Description TODO(行业Controller) 
 * @date 2018年2月28日下午4:24:37
 */
@Controller
@RequestMapping("/indus")
public class IndustryController {
	
	@Autowired
	private IndustryServiceI industryService;
	
	@RequestMapping("/list")
	public String indusList(Model model) {
		model.addAttribute("indus", industryService.indusList());
		return "system/industry/industry_view";
	}
	
	
	/**
	 * 
	 * @Title indusAdd
	 * @Description TODO(新增行业页面) 
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/add")
	public String indusAdd(Model model) {
		return "system/industry/industry_form";
	}
	
	
	/**
	 * 
	 * @Title indusEdit
	 * @Description TODO(编辑行业页面) 
	 * @param id
	 * @param model
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/edit_{id}")
	public String indusEdit(@PathVariable("id") String id, Model model) {
		model.addAttribute("indus_id", id);
		return "system/industry/industry_form";
	}
	
	
	/**
	 * 
	 * @Title indusGet
	 * @Description TODO(根据id获取行业对象) 
	 * @param id
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/get_{id}")
	@ResponseBody
	public Response indusGet(@PathVariable("id") String id) {
		return industryService.getIndustry(id);
	}
	
	/**
	 * 
	 * @Title indusFindParent
	 * @Description TODO(获取父行业) 
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/parent")
	@ResponseBody
	public Response indusFindParent() {
		return industryService.findParent();
	}
	
	/**
	 * 
	 * @Title indusCreate
	 * @Description TODO(新增行业表单提交) 
	 * @param indusRequest
	 * @param result
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/create")
	@ResponseBody
	public Response indusCreate(@Valid IndusRequest indusRequest, BindingResult result) {
		Response res = new Response();
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();  
            for (ObjectError error : list) { 
            	res.setMsg(res.getMsg() + "[" + error.getDefaultMessage() + "]");
            }  
		}else {
			Industry industry = new Industry(null, indusRequest.getIndusName(), indusRequest.getIndusSort(), new Date(), null, indusRequest.getParentId());
			res = industryService.saveIndustry(industry);
		}
		return res;
	}
	
	/**
	 * 
	 * @Title indusUpdate
	 * @Description TODO(编辑行业表单提交) 
	 * @param indusRequest
	 * @param result
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Response indusUpdate(@Valid IndusRequest indusRequest, BindingResult result) {
		Response res = new Response();
		if(StringUtils.isBlank(indusRequest.getId())) {
			res.setMsg("未传入修改对象的唯一标识");
		} else if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();  
            for (ObjectError error : list) { 
            	res.setMsg(res.getMsg() + "[" + error.getDefaultMessage() + "]");
            }  
		}else {
			Industry industry = new Industry(indusRequest.getId(), indusRequest.getIndusName(), indusRequest.getIndusSort(), null, new Date(), indusRequest.getParentId());
			res = industryService.saveIndustry(industry);
		}
		return res;
	}
	
	
	/**
	 * 
	 * @Title indusDelete
	 * @Description TODO(删除行业) 
	 * @param id
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/delete_{id}")
	@ResponseBody
	public Response indusDelete(@PathVariable("id") String id) {
		return industryService.deleteIndustry(id);
	}
}
