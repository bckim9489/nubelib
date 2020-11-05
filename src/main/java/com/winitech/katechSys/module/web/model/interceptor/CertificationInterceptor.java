package com.winitech.katechSys.module.web.model.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.winitech.katechSys.module.web.model.response.LoginDTO;
@Component
public class CertificationInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		LoginDTO userInfo = (LoginDTO)session.getAttribute("userInfo");
		if(ObjectUtils.isEmpty(userInfo)) {
			response.sendRedirect("/login");
			return false;
		} else {
			session.setMaxInactiveInterval(30*60);
			return true;

		}


	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

}
