package org.launchcode.shield.controllers;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.shield.models.User;
import org.launchcode.shield.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController extends AbstractController {

	@Autowired
	private UserDao userDao;
		
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}
		
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {
		
		//Sign up the user name, password, and verification
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
			
		
		User prev_user = userDao.findByUsername(username);
		if (prev_user != null) {
			model.addAttribute("username_error", "Username is already taken");
			return "signup";
		}
			
		
		if (User.isValidUsername(username) && User.isValidPassword(password) && password.equals(verify)){
			//This is if everything passes
			//need to add user to database
			User user = new User(username, password);
			userDao.save(user);
			this.setUserInSession(request.getSession(), user);
			return "redirect:/shield/newcalc"; //TODO change this so it reflects equation not post
			}
			
		if (!User.isValidPassword(password)){
			//This occurs if the password isn't valid
			model.addAttribute("password_error", "Your password is invalid");
			}
				
		if (!User.isValidUsername(username)){
			//this occurs if the username isn't valid
			model.addAttribute("username_error", "Your username is invalid");	
			
		} 
		
		if (!password.equals(verify)){
			//This occurs if the passwords do not match
			model.addAttribute("verify_error", "Your passwords do not match");
			
		} 
		return "signup";
	}
		
		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public String loginForm() {
			return "login";
		}
		
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public String login(HttpServletRequest request, Model model) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
		
			User user = userDao.findByUsername(username);
			if (user == null) {
				model.addAttribute("error", "Username does not exist");
				return "login";
			}
			//need to then set the user in the specific session instance
			if (user.isMatchingPassword(password)) {
				this.setUserInSession(request.getSession(), user);
				return "redirect:shield/newcalc";//TODO change this so it reflects equation not Post
			}
			
			model.addAttribute("error", "Incorrect password");
			return "login";
			
		}
		
		@RequestMapping(value = "/logout", method = RequestMethod.GET)
		public String logout(HttpServletRequest request){
	        request.getSession().invalidate();
			return "redirect:/";
		}
		
		
}
