package com.jinmark.core.bean.weChat;

/**
 * 接如JSSDK时权限验证所需配置参数对象
 * QC
 * 2017年2月17日 下午2:46:36
 */
public class JSSDKConfigParam {
	/**
	 * 公众号的唯一标识
	 */
	private String appId;
	/**
	 * 生成签名的时间戳
	 */
	private long timestamp;
	/**
	 * 生成签名的随机串
	 */
	private String nonceStr;
	/**
	 * 签名
	 */
	private String signature;
	
	
	public JSSDKConfigParam() {
		super();
	}
	
	public JSSDKConfigParam(String appId, long timestamp, String nonceStr, String signature) {
		super();
		this.appId = appId;
		this.timestamp = timestamp;
		this.nonceStr = nonceStr;
		this.signature = signature;
	}

	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
}
