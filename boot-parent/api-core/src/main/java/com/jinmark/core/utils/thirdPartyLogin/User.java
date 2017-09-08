package com.jinmark.core.utils.thirdPartyLogin;
//暂时类
public class User {
	private String id;//主键id
	private String account;// 本地登录账号
	private String passwd;// 登录密码
	private String thirdId;// 第三方平台账号(openID)
	private String nickName;// 昵称
	private byte[] portraitData;// 头像图片的二进制数据
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getThirdId() {
		return thirdId;
	}
	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public byte[] getPortraitData() {
		return portraitData;
	}
	public void setPortraitData(byte[] portraitData) {
		this.portraitData = portraitData;
	}
	
	
}
