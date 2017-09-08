package com.jinmark.core.bean.weChat;
/**
 * 微信access_token返回对象
 * QC
 * 2016年12月23日 上午10:58:22
 */
public class AccessTokenResponse {
	/**
	 * access_token
	 */
	private String access_token;
	/**
	 * expires_in有效期
	 */
	private int expires_in;
	/**
	 * 错误码
	 */
	private int errcode;
	/**
	 * 错误信息
	 */
	private String errmsg;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
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
	
}
