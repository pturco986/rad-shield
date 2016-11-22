package com.launchcode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.launchcode.shield.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.AbstractController;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	UserDao userDao;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
	
		List<String> authPages = Arrays.asList("/shield/calculate");
		
		//Require sign in for auth pages
		if (authPages.contains(request.getRequestURI())) {
			
			boolean isLoggedIn = false;
			User user;
			Integer userID = (Integer) request.getSession().getAttribute(AbstractController.userSessionKey);
			
			if (userID != null) {
				user = userDao.findbyUid(userId);
				
				if (user != null) {
					isLoggedIn = true;
					
				}
			}
		
			//If user not logged in, redirect to login page
			if(!isLoggedIn) {
				response.sendRedirect("/login");
				return false;
			}
		}
		return true;
	}
}
