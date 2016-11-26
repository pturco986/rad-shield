package org.launchcode.shield.controllers;

import java.util.List;

import org.launchcode.shield.models.User;
import org.launchcode.shield.models.Equation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class ShieldController extends AbstractController{

	@RequestMapping(value = "/")
	public String index(Model model){
		
		List<User> users = userDao.findAll(); //this finds all the users in the database
		model.addAttribute("users", users); //calls the users in the database
		return "index"; 
	}
	
	@RequestMapping(value = "/shield") //TODO make sure this matches the HTML
	public String calculationsIndex(Model model) {
		
		// TODO - fetch posts and pass to template figure out what is wrong with the Dao
		Iterable<Equation> equations = equationDao.findAll();
		model.addAttribute("equations", equations);
		return "shield"; // TODO make sure this is what I want to name the actual file
	}
	
}
