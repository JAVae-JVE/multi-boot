package com.jinmark.sys.service.login.impl;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinmark.core.Constants;
import com.jinmark.core.bean.Response;
import com.jinmark.sys.config.shiro.PasswordHelper;
import com.jinmark.sys.domain.SysUser;
import com.jinmark.sys.repository.SysUserRepository;
import com.jinmark.sys.service.login.LoginServiceI;

@Service
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginServiceI {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysUserRepository userRepository;
	
	@Override
	public Response login(String username, String password) {
		Response res = new Response();
		SysUser user = userRepository.findByUsername(username);
		if(user == null ) {//用户不存在
			res.setMsg("账号不存在！");
		} else {
			SysUser user2 = new SysUser(username, password, user.getSalt());
			UsernamePasswordToken token = new UsernamePasswordToken(username, PasswordHelper.encryptPasswordValidation(user2));
			//token.setRememberMe(true);
			logger.info("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
			//获取当前的Subject
		    Subject currentUser = SecurityUtils.getSubject();
		    Session session = currentUser.getSession();
		    //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
		    //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
		    //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
		    try {
		    	currentUser.login(token);
		    	session.setAttribute(Constants.CURRENT_USER, user);
		    	res.setSuccess(true);
			}catch(UnknownAccountException uae){
				uae.printStackTrace();
				logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
				res.setMsg("账号不存在！");
			}catch(IncorrectCredentialsException ice){
		    	ice.printStackTrace();
		    	logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
		    	res.setMsg("账号或密码错误！");
		    }catch(LockedAccountException lae){
		    	lae.printStackTrace();
		    	logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
		    	res.setMsg("账号已锁定！");
		    }catch(ExcessiveAttemptsException eae){
		    	logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
		    	res.setMsg("账号或密码错误次数过多！");
		    }catch(AuthenticationException ae){
		    	ae.printStackTrace();
		    	//通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
		    	logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
		    	res.setMsg("账号或密码错误！");
		    }
		    if(!res.isSuccess() || !currentUser.isAuthenticated()) {
		    	token.clear();
		    	session.removeAttribute(Constants.CURRENT_USER);
		    }
		}
		return res;
	}

}
