package com.jinmark.core.bean.weChat;
/**
 * 获取jsapi_ticket返回对象
 * QC
 * 2017年2月17日 下午2:05:31
 */
public class JsapiTicketResponse {
	/**
	 * 错误码
	 */
	private int errcode;
	/**
	 * 错误消息
	 */
	private String errmsg;
	/**
	 * jsapi_ticket
	 */
	private String ticket;
	/**
	 * 有效期
	 */
	private int expires_in;
	
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	
}
