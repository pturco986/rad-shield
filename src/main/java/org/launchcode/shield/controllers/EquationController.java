package org.launchcode.shield.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.shield.models.Equation;
import org.launchcode.shield.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EquationController extends AbstractController{
	
	@RequestMapping(value ="/shields/newcalc", method = RequestMethod.GET)
	public String newEquationForm() {
		return "newcalc";
	}
	//TODO make sure to add the proper html file names TODO Double check
	@RequestMapping(value = "/shields/newcalc", method = RequestMethod.POST)
	public String newEquation(HttpServletRequest request, Model model) {
		String patients = request.getParameter("patients");
		String occupancy = request.getParameter("occupancy");
		String limit = request.getParameter("limit");
		String distance = request.getParameter("distance");
		User user = this.getUserFromSession(request.getSession());
		// TODO implement the newcalc, request parameters, validation
		// parameters, and if it is valid do the calculation
		
		if (patients != null && occupancy != null && limit != null && 
			distance != null && patients != "" &&
			occupancy!= "" && limit != "" &&  
			distance != "") {
	
		Equation equation = new Equation(patients, occupancy, limit, distance, user);
		equationDao.save(equation);
		return String.format("redirect:/shields/%s/%s", user.getUsername(), equation.getUid());
		//redirects to the new calculation for the shielding amount
		}
		
		if (patients == null || patients == "" ||  limit == null || limit == "" ||
			occupancy == null || occupancy == "" || distance == null ||
			distance == ""){
			model.addAttribute("error", "You cannot leave a field blank");
		}
		return "newcalc";
	}
	
	//handles requests specific to a username and uid
	@RequestMapping(value = "shields/{username}/{uid}", method = RequestMethod.GET)
	public String singleEquation(@PathVariable String username, @PathVariable int uid, Model model) {
		//TODO implementation of a single equation
		Equation equation = equationDao.findByUid(uid);
		model.addAttribute("equation", equation);
		return "equation";
	}
	
	@RequestMapping(value ="shields/{username}", method = RequestMethod.GET)
	public String userEquations(@PathVariable String username, Model model) {
		//TODO Implementation of a user's equations
		User author = userDao.findByUsername(username);
		List<Equation> equations = author.getEquations();
		model.addAttribute("equations", equations);
		return "shields";
	}
}
