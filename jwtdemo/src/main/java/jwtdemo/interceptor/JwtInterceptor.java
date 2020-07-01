package jwtdemo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jwtdemo.service.SysService;
import jwtdemo.utils.CryiptionUtil;
import jwtdemo.utils.JwtUtils;

/**
 * @author xiajinhui
 * 2020年6月29日
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
	@Autowired
	private SysService SysService;
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//从request中取出token
		String token=request.getHeader("token");
		//如果不是映射到方法，直接通过
		if(!(handler instanceof HandlerMethod)) {
			return true;
		}
		
		if(token !=null) {
			//可以把这个token放入到redis中，比如退出的时候直接使token失效，只要删除redis中的token就可以了
			//在认证之前先查看redis中是否有该token，若无直接跳转到登陆界面(或者存入cookie中，然后设置一个threadLocal,存放当前查找的用户)
			String username=JwtUtils.getUserNameByToken(request);
			//拿到用户名去数据库获得密码，然后在对比，密钥为用户密码
			boolean result=JwtUtils.verify(token, username, SysService.getPasswordFormDataBase(username));
			if(result) {
				System.out.println("通过拦截器");
				//在这里将token放入redis服务器中，设置失效时间半个小时
				//redis.put(token)
                return true;
			}
		}
		return false;
		//也可以直接跳转到登陆页面
		//response.sendRedirect("/login/login.html");
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
