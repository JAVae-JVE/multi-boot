package com.jinmark.sys.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author QC
 * @ClassName GlobalExceptionController
 * @Description TODO(全局异常处理controller) 
 * @date 2017年9月1日下午5:15:20
 */
@Controller
public class GlobalExceptionHandler {
	/**
	 * 
	 * @Title return404
	 * @Description TODO(页面未找到) 
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/404")
	public String return404() {
		return "404";
		
	}
	
	/**
	 * 
	 * @Title return500
	 * @Description TODO(服务器内部错误) 
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/500")
	public String return500() {
		return "500";
		
	}
}
