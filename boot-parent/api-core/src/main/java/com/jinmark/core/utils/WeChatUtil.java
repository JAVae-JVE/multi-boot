package com.jinmark.core.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.apache.tools.ant.taskdefs.condition.Os;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.core.bean.weChat.AccessTokenResponse;
import com.api.core.bean.weChat.JsapiTicketResponse;
import com.api.core.bean.weChat.OpenIdResponse;
import com.api.core.bean.weChat.WeChatUserInfo;
import com.api.core.bean.weChat.templateMessage.WechatTemplate;

/**
 * 微信工具类
 * QC
 * 2017年1月10日 上午9:07:14
 */
public class WeChatUtil {
	
	private static Logger logger = Logger.getLogger(WeChatUtil.class);
	
	
	private static String token = "mhw_token";
	//正式环境
	/*public static final String APPID = "wxc6ac237ccbd32e10";
	public static final String APPSECRET = "7dd09c5f31866ed79c264cb2104e12c0";*/
	public static final String APPID = "wx687ca8bb6ad921c4";
	public static final String APPSECRET = "1fab6fcef85926d03a1f16fd5f82f5e0";
	//微信访问域名
	public static final String WE_CHAT_URL = "http://sofmit.designmhw.com";
	//登录微信地址
	public static final String WE_CHAT_LOGIN_URL = WE_CHAT_URL + "/mhw-app/Wechat/LoginWechat.htm";
	//个人设置地址
	public static final String WE_CHAT_SETTING_URL = WE_CHAT_URL + "/mhw-app/Wechat/personal_settings.htm";
	/**
	 * 发送模板消息地址
	 */
	public static final String SEND_TEMPLATE_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	/**
	 * 下载多媒体文件地址
	 */
	public static final String GET_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	/**
	 * 微信消息模板id
	 */
	/*public static final String template_id0 = "oyw8vcylvT7wb30vrl284fQ5928IkB-o29RAMG4O4ss";
	public static final String template_id1 = "97lQCIGlRANM1wxrp_B9Qt5HK-O1ohA0tRG8zkYgfzQ";
	public static final String template_id2 = "pgkxndAOjypASvgbK_GpjRaSN7At5VkVCH3VJDtTMQc";*/
	public static final String template_id0 = "JrB-Rolde7v2TFVffYVCWUrlrIh2EXwkilZuTlHABc0";
	public static final String template_id1 = "FIy0xYQx8d7KXU0-0jH0pW0nVcJI-dmQpIVTUMGdf1Y";
	public static final String template_id2 = "TppIrjFPZn1lHMQcUIrg5lZNX82oOHXr2KYlj6atw38";
	public static final String template_id3 = "JrB-Rolde7v2TFVffYVCWUrlrIh2EXwkilZuTlHABc0";
	public static final String template_id4 = "FIy0xYQx8d7KXU0-0jH0pW0nVcJI-dmQpIVTUMGdf1Y";
	public static final String template_id5 = "TppIrjFPZn1lHMQcUIrg5lZNX82oOHXr2KYlj6atw38";
	public static final String template_id6 = "JrB-Rolde7v2TFVffYVCWUrlrIh2EXwkilZuTlHABc0";
	public static final String template_id7 = "FIy0xYQx8d7KXU0-0jH0pW0nVcJI-dmQpIVTUMGdf1Y";
	public static final String template_id8 = "TppIrjFPZn1lHMQcUIrg5lZNX82oOHXr2KYlj6atw38";
	public static final String template_id9 = "JrB-Rolde7v2TFVffYVCWUrlrIh2EXwkilZuTlHABc0";
	public static final String template_id10 = "FIy0xYQx8d7KXU0-0jH0pW0nVcJI-dmQpIVTUMGdf1Y";
	
	
	
	
	/**
	 * ********************微信签名验证***********************
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return boolean
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String checktext = null;
		if(StringUtils.isNotBlank(signature)) {
			//1. 将token、timestamp、nonce三个参数进行字典序排序
			String[] paramArr = new String[] {token,timestamp,nonce};
			Arrays.sort(paramArr);
			//2. 将三个参数字符串拼接成一个字符串进行sha1加密
			String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-1");
				byte[] digest = md.digest(content.getBytes());
				checktext = byteToStr(digest);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		//3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		return checktext != null ? checktext.equals(signature.toUpperCase()) : false;
	}
	
	/**
	 * ************************生成JS-SDK权限验证的签名**************************
	 * @param noncestr 随机字符串
	 * @param timestamp 时间戳
	 * @param url 当前网页的URL，不包含#及其后面部分
	 * @return String
	 */
	public static String getJsSDKSignature(String noncestr, long timestamp, String url) {
		String jsSDKSignature = null;
		StringBuilder string1 = new StringBuilder("jsapi_ticket=");
		string1.append(getJsapiTicket())
		.append("&noncestr=").append(noncestr)
		.append("&timestamp=").append(timestamp)
		.append("&url=").append(url);
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(string1.toString().getBytes());
			jsSDKSignature = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return jsSDKSignature;
	}
	/**
	 * ********************获取jsapi_ticket***********************
	 * @return
	 * @return String
	 */
	public static String getJsapiTicket() {
		String jsapi_ticket = null;
		String jsapiTicketJson = null;
		try {
			jsapiTicketJson = HttpClientUtils.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + getAccessToken() + "&type=jsapi");
			
			if(StringUtils.isNotBlank(jsapiTicketJson)) {
				JsapiTicketResponse jsapiTicketResponse = JSON.parseObject(jsapiTicketJson, JsapiTicketResponse.class);
				if(jsapiTicketResponse != null && StringUtils.isNotBlank(jsapiTicketResponse.getTicket())) {
					jsapi_ticket = jsapiTicketResponse.getTicket();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("jsapi_ticket:" + jsapi_ticket);
		
		return jsapi_ticket;
	}
	
	
	/**
	 * ********************获取access_token***********************
	 * @return String access_token
	 */
	public static String getAccessToken() {
		String access_token = null;
		String accessTokenJson = null;
		try {
			accessTokenJson = HttpClientUtils.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ APPID +"&secret=" + APPSECRET);
			
			if(StringUtils.isNotBlank(accessTokenJson)) {
				AccessTokenResponse accessTokenResponse = JSON.parseObject(accessTokenJson, AccessTokenResponse.class);
				if(accessTokenResponse != null && StringUtils.isNotBlank(accessTokenResponse.getAccess_token())) {
					access_token = accessTokenResponse.getAccess_token();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("access_token:" + access_token);
		
		
		return access_token;
	}
	
	/**
	 * ********************获取openId***********************
	 * @param code
	 * @return String
	 */
	public static String getOpenId(String code) {
		String openId = null;
		//通过code取openID
		String openIdJson = null;
		try {
			openIdJson = HttpClientUtils.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ APPID +"&secret="+ APPSECRET +"&code="+ code +"&grant_type=authorization_code");
		
			if(StringUtils.isNotBlank(openIdJson)) {
				OpenIdResponse openIdResponse = JSON.parseObject(openIdJson, OpenIdResponse.class);
				if(openIdResponse != null && StringUtils.isNotBlank(openIdResponse.getOpenid())) {
					openId = openIdResponse.getOpenid();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return openId;
	}
	
	/**
	 * 下载多媒体文件并保存到自己的服务器
	 * @param media_id
	 * @param access_token
	 * @param saveDir  路径
	 * @return String 返回数据库保存文件地址
	 */
	public static String getMediaFileAndSave(String media_id, String access_token, String saveDir) {
		String dbSavePath = null;
		String requestUrl = GET_MEDIA_URL.replace("ACCESS_TOKEN", access_token).replace("MEDIA_ID", media_id);
		BufferedInputStream is = null;
		FileOutputStream os = null;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
		
			File dir = new File(saveDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			String contentDisposition = conn.getHeaderField("Content-disposition");
			String dbFileName = UUID.randomUUID().toString().replace("-", "")
					+ contentDisposition.substring(contentDisposition.lastIndexOf("."), contentDisposition.length() -1);
			
			StringBuffer path = new StringBuffer(saveDir);
			path.append(File.separator).append(dbFileName);
			
			is = new BufferedInputStream(conn.getInputStream());
			os = new FileOutputStream(new File(path.toString()));
			byte[] bytes = new byte[2*1024];
			int len;
			while((len = is.read(bytes)) != -1) {
				os.write(bytes, 0, len);
			}
			
			dbSavePath = dbFileName;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if(is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return dbSavePath;
	}
	
	/**
	 * ********************获取微信用户信息***********************
	 * @param access_token
	 * @param openid
	 * @return WeChatUserInfo
	 */
	public static WeChatUserInfo getWeChatUserInfo(String access_token, String openid) {
		//获取微信信息
		String bi = null;
		try {
			bi = HttpClientUtils.get("https://api.weixin.qq.com/cgi-bin/user/info?access_token="+ access_token +"&openid="+ openid +"&lang=zh_CN");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi == null ? null : JSON.parseObject(bi, WeChatUserInfo.class);
	}
	
	/**
	 * ********************发送模板消息***********************
	 * @param wechatTemplate
	 * @return void
	 */
	public static void sendTemplateMessage(WechatTemplate wechatTemplate) {
		if(wechatTemplate != null) {
			String access_token = getAccessToken();
			if(StringUtils.isNotBlank(access_token)) {
				String url = SEND_TEMPLATE_MSG_URL.substring(0, SEND_TEMPLATE_MSG_URL.indexOf("ACCESS_TOKEN")) + access_token;
				logger.info("url------->" + url); 
				String result = HttpClientUtils.post(url, JSON.toJSONString(wechatTemplate));
				logger.info("result------->" + result);  
				JSONObject jsonObject = JSON.parseObject(result);
				if (null != jsonObject) {  
			        int errorCode = jsonObject.getIntValue("errcode");         
			        if (0 == errorCode) {  
			            logger.info("模板消息发送成功");  
			        } else {  
			            String errorMsg = jsonObject.getString("errmsg");  
			            logger.info("模板消息发送失败,错误是 "+errorCode+",错误信息是"+ errorMsg);  
			        }  
			    }  
			}
		}
	}
	
	/**
	 * 将字节数组转换为十六进制字符串
	 * @param byteArray
	 * @return
	 * @return String
	 */
	private static String byteToStr(byte[] byteArray) {
		StringBuffer strDigest = new StringBuffer("");
		for(int i = 0; i < byteArray.length;i++) {
			strDigest.append(byteToHexStr(byteArray[i]));
		}
		return strDigest.toString();
	}
	/**
	 * 将字节转换为十六进制字符串
	 * @param mByte
	 * @return String
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit ={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
}
