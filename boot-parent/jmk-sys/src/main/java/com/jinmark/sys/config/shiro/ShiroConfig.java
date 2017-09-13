package com.jinmark.sys.config.shiro;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
@Configuration
public class ShiroConfig {
	
	/**
	 * 身份认证realm;
     * (这个需要自己写，账号密码校验；权限等)
	 * @return MyShiroRealm
	 */
	@Bean
    public MyShiroRealm myShiroRealm(){
		return new MyShiroRealm();
    }
	
	/**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager(){
       System.out.println("ShiroConfiguration.getEhCacheManager()");
       EhCacheManager cacheManager = new EhCacheManager();
       cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
       return cacheManager;
    }
	
	@Bean
    public SecurityManager securityManager() {
       DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
       securityManager.setRealm(myShiroRealm());
     //注入缓存管理器;
       //securityManager.setCacheManager(ehCacheManager());//这个如果执行多次，也是同样的一个对象;
       return securityManager;

    }
	
	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
        Filter Chain定义说明

       1、一个URL可以配置多个Filter，使用逗号分隔

       2、当设置多个过滤器时，全部验证通过，才视为通过

       3、部分过滤器可指定参数，如perms，roles
	 * @param securityManager
	 * @return ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager 
       shiroFilterFactoryBean.setSecurityManager(securityManager);
       //拦截器.
       Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
       //druid监控页面不需要拦截
       filterChainDefinitionMap.put("/druid/**", "anon");
       //静态资源不拦截
       filterChainDefinitionMap.put("/css/**", "anon");
       filterChainDefinitionMap.put("/fonts/**", "anon");
       filterChainDefinitionMap.put("/i/**", "anon");
       filterChainDefinitionMap.put("/img/**", "anon");
       filterChainDefinitionMap.put("/js/**", "anon");
       
       //登录页和登录方法不需要拦截
       filterChainDefinitionMap.put("/login_page", "anon");
       filterChainDefinitionMap.put("/login", "anon");
       //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
       filterChainDefinitionMap.put("/logout", "logout");
       //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
       filterChainDefinitionMap.put("/**", "authc");
       // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login_page");
        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
       shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
       return shiroFilterFactoryBean;
	}
	
	/**
	 * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
	 * @param securityManager
	 * @return AuthorizationAttributeSourceAdvisor
	 */
	@Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
       AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
       authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
       return authorizationAttributeSourceAdvisor;

    }
	
	/**
	 * 
	 * @Title shiroDialect
	 * @Description TODO(在thymeleaf里使用shiro的标签的bean) 
	 * @return
	 * @return ShiroDialect  返回类型 
	 * @throws
	 */
	@Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
