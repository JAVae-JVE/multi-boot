package com.jinmark.sys.config.shiro;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import com.jinmark.sys.consist.Constants;
import com.jinmark.sys.domain.SysUser;
import com.jinmark.sys.service.permission.SysUserServiceI;


public class MyShiroRealm extends AuthorizingRealm {
	
	@Resource
	private SysUserServiceI userService;
	
	

	/**
	 * 授权操作
	 * 此方法调用  hasRole,hasPermission的时候才会进行回调.
	 * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*
        * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
        * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
        * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
        * 缓存过期之后会再次执行。
        */
		
		String username = (String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));

        return authorizationInfo;
	}

	/**
	 * 身份验证操作
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		/**
		 该方法主要执行以下操作:

			1、检查提交的进行认证的令牌信息
			2、根据令牌信息从数据源(通常为数据库)中获取用户信息
			3、对用户信息进行匹配验证。
			4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例。
			5、验证失败则抛出AuthenticationException异常信息。
		 **/
		
		
		//获取基于用户名和密码的令牌  
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的  
        //两个token的引用都是一样的
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;  
		

        SysUser user = userService.findByUsername(usernamePasswordToken.getUsername());

        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if(user.isLocked()) {
            throw new LockedAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        this.setSession(Constants.CURRENT_USER, user);
        return authenticationInfo;
	}
	
	/** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see  比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            if(null != session){  
            	System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
                session.setAttribute(key, value);  
            }  
        }  
    }  

}
