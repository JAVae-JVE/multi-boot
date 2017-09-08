package com.jinmark.core.filter;

import java.lang.reflect.Method;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jinmark.core.annotation.Token;


public class TokenInterceptor extends HandlerInterceptorAdapter {
	
	private final String TOKEN = "token";
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Token annotation = method.getAnnotation(Token.class);
			if (annotation != null) {
				boolean needSaveSession = annotation.save();
				if (needSaveSession) {
					request.getSession(false).setAttribute(TOKEN,
							UUID.randomUUID().toString());
				}
				boolean needRemoveSession = annotation.remove();
				if (needRemoveSession) {
					if (isRepeatSubmit(request)) {
						//若重复提交可在此给出相应提示
						//request.setAttribute("msg", "注册开放时间：9：00-12：00");    
			            //request.getRequestDispatcher("/msg.jsp").forward(request, response);    
						return false;
					}
					request.getSession(false).removeAttribute(TOKEN);
				}
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}
	/**
	 * 是否重复提交
	 * @param request request
	 * @return boolean 返回类型
	 */
	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false).getAttribute(
				"token");
		if (serverToken == null) {
			return true;
		}
		String clinetToken = request.getParameter("token");
		if (clinetToken == null) {
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}

}
