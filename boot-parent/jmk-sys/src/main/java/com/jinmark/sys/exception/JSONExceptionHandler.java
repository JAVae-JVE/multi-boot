package com.jinmark.sys.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinmark.core.bean.Response;

@ControllerAdvice
public class JSONExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 返回json数据的全局异常
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 * @return ResponseEntity<Result>
	 */
	@ExceptionHandler(JSONException.class)
    @ResponseBody
    public ResponseEntity<Response> defaultErrorHandler(HttpServletRequest req, JSONException e) throws Exception {
		ResponseEntity<Response> result = new ResponseEntity<Response>(new Response(false, "服务器内部错误"), HttpStatus.INTERNAL_SERVER_ERROR);
		logger.error(result.toString());
		return result;
    }
	
	/**
	 * 返回统一异常页面的全局异常
	 * @param req
	 * @param e
	 * @return
	 * @return String
	 */
	/*@ExceptionHandler(Exception.class)
	public String exceptionHandler(HttpServletRequest req,Exception e) {
		logger.info("URL——》" + req.getRequestURL());
		logger.info("Exception——》" + e.getMessage());
		e.printStackTrace();
		return "500";
	}*/
}
