package com.accp.web.interceptors;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.accp.service.FootService;
import com.accp.service.NavigationBarService;
import com.accp.web.util.Common;
import com.accp.web.util.WebTools;

/**
 * 初识化数据拦截器类
 * @author Y2项目:李旭强
 *
 */
public class InitialKnowledgeDataInterceptor implements HandlerInterceptor {
	@Autowired
	private NavigationBarService navigationBarService ;
	@Autowired
	private FootService footService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//初始化导航栏
		Object navigationBar=WebTools.getSession(Common.NAVIGATIONBARS);
		if(navigationBar == null) {
			WebTools.setSession(Common.NAVIGATIONBARS, navigationBarService.getAllNavigationBar());
		}
		
		//初始化友情
		Object foot=WebTools.getSession(Common.FOOTS);
		if(foot == null) {
			WebTools.setSession(Common.FOOTS, footService.getAllFoot());
		}
		
		
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
