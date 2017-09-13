package com.jinmark.sys.service.login;

import com.jinmark.sys.vo.Response;
/**
 * 
 * @author QC
 * @ClassName LoginServiceI
 * @Description TODO(登录service接口) 
 * @date 2017年8月25日上午10:47:09
 */
public interface LoginServiceI {
	/**
	 * 
	 * @Title login
	 * @Description TODO(登录) 
	 * @param username
	 * @param password
	 * @return Response  返回类型 
	 * @throws
	 */
	Response login(String username, String password);
}
