package com.jinmark.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class HttpClientUtils {
	private static Logger logger = Logger.getLogger(HttpClientUtils.class);
	public static String get(String url) throws ClientProtocolException, IOException {
		
		String content = null;
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		
		RequestConfig config = RequestConfig.custom()
			    .setConnectionRequestTimeout(60000).setConnectTimeout(60000)
			    .setSocketTimeout(60000).build();
		
		CloseableHttpResponse response = null;
		
		get.setConfig(config);
		response = httpClient.execute(get);
		if(response.getStatusLine().getStatusCode() == 200) {
			content = EntityUtils.toString(response.getEntity());
		}
		
		return content;
	}
	
	/**
	 * 发送JSON格式的HttpPOST请求
	 * @param url
	 * @param obj
	 * @return String
	 */
	public static String post(String url,String outputStr) {
		logger.info("***********"+ outputStr);
		
		String content = null;
		try {
			URL u = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setDoOutput(true);
	        connection.setDoInput(true);
	        connection.setRequestMethod("POST");
	        connection.setUseCaches(false);
	        //connection.setInstanceFollowRedirects(true);
	        connection.setRequestProperty("Content-Type",
	                "application/x-www-form-urlencoded");
	     // 当outputStr不为null时向输出流写数据
	        if (StringUtils.isNotBlank(outputStr)) {
                OutputStream outputStream = connection.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
	        
	        //读取响应
	        BufferedReader reader = new BufferedReader(new InputStreamReader(
	                connection.getInputStream(),"utf-8"));
	        String lines = null;
	        StringBuffer sb = new StringBuffer("");
	        while ((lines = reader.readLine()) != null) {
	            //lines = new String(lines.getBytes(), "utf-8");
	            sb.append(lines);
	        }
	        reader.close();
            connection.disconnect();
            content = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(content);
		return content;
	}
}
