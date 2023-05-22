package com.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight.entity.User;
import com.flight.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepo;
	
	//http://localhost:8080/flights/login
	@RequestMapping("/login")
	public String login() {
		return "login/login";
	}
	//http://localhost:8080/flights/showReg
	@RequestMapping("/showReg")
	public String showreg() {
		return "login/showReg";
	}
	//http://localhost:8080/flights/saveReg
	@RequestMapping("/saveReg")
	public String saveNewRegistration(@ModelAttribute("user") User user) {
		userRepo.save(user);
		return "login/login";
	}
	
//	@RequestMapping("/verifyLogin")
//	public String verify(@RequestParam ("email")String email,@RequestParam("password") String password,ModelMap modelMap) {
//		User user = userRepo.findByEmail(email);
//		if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
//			return "findflights";
//		}
//		else {
//			return "login/login";
//		}
//	}
	@RequestMapping("/verifyLogin")
	public String verifyLogin(@RequestParam("email") String email, @RequestParam("password") String password,ModelMap modelMap) {
		User user = userRepo.findByEmail(email);
		if(user!=null) {
		if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
			return "findflights";
		}else {
			modelMap.addAttribute("error", "Invalid username/password");
			return "login/login";
		}
		}else {
			modelMap.addAttribute("error", "Invalid username/password");
			return "login/login";
		}
}
}