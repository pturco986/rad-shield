package org.launchcode.shield.controllers;

import javax.servlet.http.HttpSession;

import org.launchcode.shield.models.User;
import org.launchcode.shield.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController {
	
	@Autowired
	protected UserDao userDao;
	public static final String userSessionKey = "user_id";
	
	protected User getUserFromSession(HttpSession session) {
		
		Integer userId = (Integer) session.getAttribute(userSessionKey);
		return userId == null ? null : userDao.findByUid(userId);
	}
	
	protected void setUserInSession(HttpSession session, User user) {
		session.setAttribute(userSessionKey, user.getUid());
	}
 }
