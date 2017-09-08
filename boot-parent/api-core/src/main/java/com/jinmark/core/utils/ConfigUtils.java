package com.jinmark.core.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;


/**
 * 加载邮件配置文件
 * QC
 * 2016年12月21日 下午2:15:04
 */
public class ConfigUtils {
	private static Properties pro =null;
	  static{
		  pro= new Properties();
		  try {
			pro.load(ConfigUtils.class.getResourceAsStream("/email.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	
	public static  String getValue(String key){
		return StringUtils.trimToEmpty(pro.getProperty(key));
	}
	
	/*public static  void  main(String args[]){
		System.out.print(ConfigUtils.getValue("email.username"));
	}*/
}
