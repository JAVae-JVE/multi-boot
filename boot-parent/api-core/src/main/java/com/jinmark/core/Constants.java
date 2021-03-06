package com.jinmark.core;

import java.io.File;

public class Constants {
	/**
	 * 默认任务组名
	 */
	public static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
	/**
	 * 默认触发器组名
	 */
	public static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";
	/**
	 * WebSocketSession 里面的用户唯一标识
	 */
	public static String SOCKET_UID = "uid";
	
	/**
	 * 防止表单重复提交
	 */
	public static final String TOKEN_KEY = "token_key";
	/**
	 * 当前登录用户标识
	 */
	public static final String CURRENT_USER = "current_user";
	/**
	 * 电话号码
	 */
	public static final String MOBILE = "mobile";
	/**
	 * 验证码
	 */
	public static final String CODE_KEY = "code";
	
	/**
	 * 文件上传原图保存目录mnt(linux) MHW(win)
	 */
	public static final String FILEPATH = File.separator + "mnt" + File.separator +"uploadFile";
	/**
	 * 文件上传案例详情保存目录mnt(linux) MHW(win)
	 */
	public static final String FILEPATH_CASEDETAIL = File.separator + "mnt" + File.separator +"uploadFile" + File.separator + "caseDetail";
	/**
	 * 文件上传案例列表保存目录mnt(linux) MHW(win)
	 */
	public static final String FILEPATH_CASELIST = File.separator + "mnt" + File.separator +"uploadFile" + File.separator + "caseList";
	
	/**
	 * 打包文件临时文件夹mnt(linux) MHW(win)
	 */
	public static final String ZIPFILEPATH = File.separator + "mnt" + File.separator +"zipFile";
	
	
	/**
	 * 图片路径前缀uploadFile(linux) upload(win)
	 */
	public static final String PIC_URL = "/uploadFile/";
	
	
	
	/**
	 * 正则表达式：验证手机号
	 */
	public static final String REGEX_MOBILE = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";
	/**
	 * 
	 * @author QC
	 * @ClassName ResType
	 * @Description TODO(菜单资源类型) 
	 * @date 2017年9月14日下午2:11:50
	 */
	public static enum ResType {
        MENU("菜单", "menu"), BUTTON("按钮", "button");

        private String name;
        private String value;
        
		private ResType(String name, String value) {
			this.name = name;
			this.value = value;
		}
		
		public String getName() {
			return name;
		}
		public String getValue() {
			return value;
		}        
    }
	
	/*public static enum Status {
        ONE("待分单"), TWO("待接单"),THREE("已接单"), FOUR("已完成"),FIVE("关闭");

        private final String info;
        private Status(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }*/
	
	/*public static void main(String[] args) {
		System.out.println(Status.THREE.getInfo());
	}*/
	
}
