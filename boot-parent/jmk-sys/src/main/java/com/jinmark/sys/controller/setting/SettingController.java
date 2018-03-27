package com.jinmark.sys.controller.setting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/set")
public class SettingController {
	/**
	 * 
	 * @Title pswdSetting
	 * @Description TODO(密码设置页面) 
	 * @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/pswd_setting")
	public String pswdSetting() {
		return "setting/pswd_setting";
	}
	
	@RequestMapping("/avatar_setting")
	public String avatarSetting() {
		return "setting/avatar_setting";
	}
}
