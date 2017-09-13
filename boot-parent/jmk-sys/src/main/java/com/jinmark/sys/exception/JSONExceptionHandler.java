package com.jinmark.sys.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ResponseEntity<Result> defaultErrorHandler(HttpServletRequest req, JSONException e) throws Exception {
		ResponseEntity<Result> result = new ResponseEntity<Result>(new Result(false, "服务器内部错误", null), HttpStatus.INTERNAL_SERVER_ERROR);
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

class Result {
	/**
	 * 返回状态true：成功；false：失败
	 */
	private boolean success;
	/**
	 * 返回消息
	 */
	private String msg;
	/**
	 * 返回数据
	 */
	private Object data;
	
	public Result() {
		super();
	}
	public Result(boolean success, String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Result [success=" + success + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
}
