package com.jinmark.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import com.jinmark.core.bean.Response;

/**
 * 短信服务接口
 * QC
 * 2016年12月21日 下午1:59:27
 */
public class SMSUtil {
	
	
	//云通讯
	public static String url = "http://sapi.253.com/msg/";// 应用地址
	public static String account = "huahui-1";// 账号
	public static String pswd = "MhW82916868";// 密码
	
	
	public static boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
	public static String extno = null;// 扩展码
	
	
	
	/**
	 * 云通讯
	 * @param mobile 电话号码
	 * @param msg 短信内容
	 * @return Result
	 */
	public static Response sendMessage(String mobile, String msg){
		Response res;
		HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
		GetMethod method = new GetMethod();
		try {
			URI base = new URI(url, false);
			method.setURI(new URI(base, "HttpBatchSendSM", false));
			method.setQueryString(new NameValuePair[] { 
					new NameValuePair("account", account),
					new NameValuePair("pswd", pswd), 
					new NameValuePair("mobile", mobile),
					new NameValuePair("needstatus", String.valueOf(needstatus)), 
					new NameValuePair("msg", msg),
					new NameValuePair("extno", extno), 
				});
			int re = client.executeMethod(method);
			if (re == HttpStatus.SC_OK) {
				InputStream in = method.getResponseBodyAsStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				
				String s = URLDecoder.decode(baos.toString(), "UTF-8");
				System.out.println(s);
				res = new Response(true, "发送成功");
			} else {
				res = new Response(true, "发送失败");
				//throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("HTTP ERROR Status: "+method.getStatusCode()+":"+method.getStatusText());
			res = new Response(true, "发送失败");
		}finally {
			method.releaseConnection();
		}
		return res;
	}
	
	public static void main(String[] args) {
		sendMessage("18161290288", "您尾号7830账户于10月21日发生人民币8670.00元行内转账。余额9870.00元。");
	}
}
