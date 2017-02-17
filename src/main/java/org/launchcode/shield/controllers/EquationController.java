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
	
	private double alpha;
	private double beta;
	private double gamma;
	private double alga;
	private double boa;
	private double onealga;
	private double kaput;
	private double pdp;
	private double kapdp;
	private double kapg;
	private double kapboa;
	private double oneboa;
	private double e;
	private double number;
	

	
	@RequestMapping(value ="/shield/newcalc", method = RequestMethod.GET)
	public String newEquationForm() {
		return "newcalc";
	}
	//TODO make sure to add the proper html file names TODO Double check
	@RequestMapping(value = "/shield/newcalc", method = RequestMethod.POST)
	public String newEquation(HttpServletRequest request, Model model) {
		String location = request.getParameter("location");
		String barrier = request.getParameter("barrier");
		String preshield = request.getParameter("preshield");
		String walltype = request.getParameter("walltype");
		String usefactor = request.getParameter("usefactor");
		String patients = request.getParameter("patients");
		String occupancy = request.getParameter("occupancy");
		String limit = request.getParameter("limit");
		String distance = request.getParameter("distance");
		User user = this.getUserFromSession(request.getSession());
		
		
		
		// TODO implement the newcalc, request parameters, validation
		// parameters, and if it is valid do the calculation
		
		if (location != null && location != "" && patients != null && occupancy != null && limit != null && 
			distance != null && patients != "" &&
			occupancy!= "" && limit != "" &&  
			distance != "") 
		{	
			Double.parseDouble(patients);
			Double.parseDouble(occupancy);
			Double.parseDouble(limit);
			Double.parseDouble(distance);
			double answer = (Double.parseDouble(patients) * Double.parseDouble(occupancy)) / (Double.parseDouble(limit) * Math.pow(Double.parseDouble(distance), 2.0));
			answer = Math.round(answer);
			
			
			
			if (barrier == "lead" && preshield == "preshielded" && walltype == "chest bucky wall") {
			alpha = 2.346;
			beta = 1.308 * Math.pow(10, 1);
			gamma = 4.982 * Math.pow(10, -1);
			boa = beta / alpha;
			alga = alpha * gamma;
			onealga = 1.0 / alga;
			oneboa = 1.0 + boa;
			kaput = (2.3 * Double.parseDouble(usefactor) * Double.parseDouble(occupancy) * Double.parseDouble(patients));
			pdp = (Double.parseDouble(limit) * Math.pow(Double.parseDouble(distance), 2));
			kapdp = kaput / pdp;
			kapg = Math.pow(kapdp, gamma);
			kapboa = kapg + boa;
			oneboa = 1.0 + boa;
			e = kapboa / oneboa;
			number = onealga * Math.log(e);
			number = Math.round(number);
			
			
		}
//			else if (barrier == "lead" && preshield == "preshielded" && walltype == "2% wall opposite chest bucky") {
//			calculate here
//			return thickness;
//		}
//			else if (barrier == "lead" && preshield == "preshielded" && walltype == "cross-table lateral wall") {
//			calculate here
//			return thickness;
//		}
//			else if (barrier == "lead" && preshield == "preshielded" && walltype == "floor") {
//			calculate here
//			return thickness;
//		}
//			else if (barrier == "lead" && preshield == "unshielded" && walltype == "chest bucky wall") {
//				calculate here
//				return thickness;
//		}
//			else if (barrier == "lead" && preshield == "unshielded" && walltype == "2% wall opposite chest bucky") {
//				calculate here
//				return thickness;
//		}
//			else if (barrier == "lead" && preshield == "unshielded" && walltype == "cross-table lateral wall") {
//			calculate here
//			return thickness;
//		}
//			else if (barrier == "lead" && preshield == "unshielded" && walltype == "floor") {
//			calculate here
//			return thickness;
//		}
//			else if (barrier == "concrete" && preshield == "preshielded" && walltype == "chest bucky wall") {
//				calculate here
//				return thickness;
//		}
//			else if (barrier == "concrete" && preshield == "preshielded" && walltype == "2% wall opposite chest bucky") {
//			calculate here
//			return thickness;
//		}
//			else if (barrier == "concrete" && preshield == "preshielded" && walltype == "cross-table lateral wall") {
//			calculate here
//			return thickness;
//		}
//			else if (barrier == "concrete" && preshield == "preshielded" && walltype == "floor") {
//			calculate here
//			return thickness;
//		}
//			else if (barrier == "concrete" && preshield == "unshielded" && walltype == "chest bucky wall") {
//				calculate here
//				return thickness;
//		}
//			else if (barrier == "concrete" && preshield == "unshielded" && walltype == "2% wall opposite chest bucky") {
//			calculate here
//			return thickness;
//		}
//			else if (barrier == "concrete" && preshield == "unshielded" && walltype == "cross-table lateral wall") {
//			calculate here
//			return thickness;
//		}
//			else if (barrier == "concrete" && preshield == "unshielded" && walltype == "floor") {
//			calculate here
//			return thickness;
//		}
			
		
			
			double thickness = number;
			Equation equation = new Equation(location, barrier, preshield, walltype, answer, thickness, user);
			equationDao.save(equation);
			model.addAttribute("equation", equation);
			return String.format("redirect:/shield/%s/%s", user.getUsername(), equation.getUid());
		//redirects to the new calculation for the shielding amount
		}
		
		if (location == null || location == "" || patients == null || patients == "" ||  limit == null || limit == "" ||
			occupancy == null || occupancy == "" || distance == null ||
			distance == "")
		{
			model.addAttribute("error", "You cannot leave a field blank");
		}
		return "newcalc";
	}
	
	//handles requests specific to a username and uid
	@RequestMapping(value = "shield/{username}/{uid}", method = RequestMethod.GET)
	public String singleEquation(@PathVariable String username, @PathVariable int uid, Model model) {
		//TODO implementation of a single equation
		Equation equation = equationDao.findByUid(uid);
		model.addAttribute("equation", equation);
		return "equation";
	}
	
	@RequestMapping(value ="shield/{username}", method = RequestMethod.GET)
	public String userEquations(@PathVariable String username, Model model) {
		//TODO Implementation of a user's equations
		User author = userDao.findByUsername(username);
		List<Equation> equations = author.getEquations();
		model.addAttribute("equations", equations);
		return "shield";
	}
}