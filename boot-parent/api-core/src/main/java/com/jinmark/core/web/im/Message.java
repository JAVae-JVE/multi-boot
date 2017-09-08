package com.jinmark.core.web.im;

import java.util.Date;

public class Message {
	/**
	 * 发送者
	 */
	private String from;
	/**
	 * 发送者名称
	 */
	private String fromName;
	/**
	 * 接收者
	 */
	private String to;
	/**
	 * 发送的文本
	 */
	private String text;
	/**
	 * 发送日期
	 */
	private Date date;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
