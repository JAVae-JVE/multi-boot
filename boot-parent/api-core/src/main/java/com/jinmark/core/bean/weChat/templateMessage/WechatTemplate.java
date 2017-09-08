package com.jinmark.core.bean.weChat.templateMessage;

import java.util.Map;


public class WechatTemplate {
	/**
	 * openID
	 */
	public String touser;
	/**
	 * 消息模板id
	 */
	public String template_id;
	/**
	 * 链接地址
	 */
	public String url;
	public String topcolor;
	private Map<String, TemplateData> data; 
	
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTopcolor() {
		return topcolor;
	}
	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}
	public Map<String, TemplateData> getData() {
		return data;
	}
	public void setData(Map<String, TemplateData> data) {
		this.data = data;
	}
	
	
}
