package com.jinmark.core.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class CookieUtils {
	/**
	 * 添加cookie
	 * @param response 响应
	 * @param cookieName  cookie名
	 * @param cookieValue cookie值
	 * @param cookieAge cookie有效起 以秒算的 1*24*60*60,当设置为0在会话期间有效
	 */
	public static void addCookie(HttpServletResponse response,String cookieName,String cookieValue,int cookieAge){
		 response.setCharacterEncoding("UTF-8");
		  Cookie  cookie=new Cookie(cookieName,cookieValue);
		  cookie.setPath("/");
	      if(cookieAge>0)
		  cookie.setMaxAge(cookieAge);//设置cookie的有效期间
		  response.addCookie(cookie);//将cookie添加到响应对象中
	}
	/**
	 * 添加cookie
	 * @param response 响应
	 * @param cookieName  cookie名
	 * @param cookieValue cookie值
	 * @param cookieAge cookie有效起 以秒算的 1*24*60*60,当设置为0在会话期间有效
	 */
	
	public static void addCookieBase64(HttpServletResponse response,String cookieName,String cookieValue,int cookieAge){
		 response.setCharacterEncoding("UTF-8"); 
		  Cookie  cookie=new Cookie(cookieName,cookieValue);
		  cookie.setPath("/");
	      if(cookieAge>0)
		  cookie.setMaxAge(cookieAge);//设置cookie的有效期间
		  response.addCookie(cookie);//将cookie添加到响应对象中
	}
	 
	/**
	 * 通过map的key 读取cookie
	 * @param request 请求对象
	 * @param cookieName cookieName
	 * @return String
	 */
	public static String getCookieValueByName(HttpServletRequest request,String cookieName){
		Map<String, Cookie> cookies=CookieUtils.createCookieMap(request);
		 if(cookies.containsKey(cookieName)){
			Cookie  cookie=(Cookie)cookies.get(cookieName);//得到集合中cookie
			return cookie.getValue();
		  }else{
		  return null;
		  }
	}
	/**
	 * 通过请求对象获取cookie并保存到map集合中，cookie的name作为map的key
	 * cookie作为value
	 * @param requst 请求对象
	 * @return Map
	 */
	public static Map<String, Cookie>  createCookieMap(HttpServletRequest requst){
		 Map<String, Cookie> cookieMap=new HashMap<String, Cookie>();
		 Cookie [] cookies=requst.getCookies();//获取cookie
		 if(cookies.length>0){
		 for(int i=0;i<cookies.length;i++){
			 cookieMap.put(cookies[i].getName(),cookies[i]);
			 System.out.println("path====");
		 }
		
		 }
		 return cookieMap;
	}
}

 