package com.jinmark.core.bean;

public class Response {
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
	private Object result;
	
	
	
	public Response() {
		super();
	}
	
	
	public Response(String msg) {
		super();
		this.msg = msg;
	}

	
	public Response(boolean success, String msg) {
		this(msg);
		this.success = success;
	}


	public Response(boolean success, String msg, Object result) {
		this(success, msg);
		this.result = result;
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
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	
}
