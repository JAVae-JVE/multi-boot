package com.jinmark.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinmark.core.bean.Response;
import com.jinmark.sys.service.CommonServiceI;

/**
 * 
 * @author QC
 * @ClassName CommonController
 * @Description TODO(公共controller) 
 * @date 2017年10月31日上午11:50:38
 */
@Controller
@RequestMapping("/common")
public class CommonController {
	
	@Autowired
	private CommonServiceI commonService;
	
	/**
	 * 
	 * @Title validateMobile
	 * @Description TODO(验证电话号码唯一性) 
	 * @param mobile
	 * @return
	 * @return Response  返回类型 
	 * @throws
	 */
	@RequestMapping("/v_m")
	@ResponseBody
	public Response validateMobile(String userId, String mobile) {
		return commonService.validateMobile(userId, mobile);
	}
}
