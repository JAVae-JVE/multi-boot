package com.jinmark.core.utils.thirdPartyLogin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;


public class OAuthHelper {

	private OAuthHelper() {}
	// 存储信息的数据结构
	private static final Map<String, OAuthInfo> infos = new HashMap<String, OAuthInfo>();
	
	// 加载信息的过程，本例中直接硬编码生成信息，也可以通过读取配置文件的方式获取
	static {
		
		String configBasePath = OAuthHelper.class.getClassLoader().getResource("/config").getPath();
		
		infos.put("baidu", new OAuthInfo(configBasePath + File.separator + "baidu.xml") {
			
			@Override
			public boolean userDataValidate(JsonNode userNode) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public User getUser(JsonNode userNode) throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
}
