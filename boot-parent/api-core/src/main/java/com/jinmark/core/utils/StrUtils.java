package com.jinmark.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import java.util.UUID;
import org.apache.commons.lang3.StringUtils;


public class StrUtils {
	/**
	 * 获取全球唯一标识
	 * @return String
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 获取客户端ip
	 * @param request
	 * @return String
	 */
	public static String getIp(HttpServletRequest request) {
       String ip = request.getHeader("X-Forwarded-For");
       if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
	
	
	public static boolean verification(String reg, String str){
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		return m.matches();		
	}
	/**
	 * 判断字符串是否包含特殊字符
	 * @param str 字符串
	 * @return boolean
	 */
	public static boolean findSpecialCharacters(String str){
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();		
	}
	
	
	
}
