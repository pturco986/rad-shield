package org.launchcode.shield.controllers;

import java.util.List;

import org.launchcode.shield.models.User;
import org.launchcode.shield.models.Equation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class CalculationsController extends AbstractController{

	@RequestMapping(value = "/")
	public String index(Model model){
		
		// TODO - fetch users and pass to template
		List<User> users = userDao.findAll(); //this finds all the users in the database
		model.addAttribute("users", users); //calls the users in the database
		return "index"; 
	}
	
	@RequestMapping(value = "/calculations")
	public String calculationsIndex(Model model) {
		
		// TODO - fetch posts and pass to template
		Iterable<Equation> equations = equationDao.findAll();
		model.addAttribute("equations", equations);
		return "calculations";
	}
	
}
