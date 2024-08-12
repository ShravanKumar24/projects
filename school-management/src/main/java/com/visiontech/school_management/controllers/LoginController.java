package com.visiontech.school_management.controllers;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.visiontech.school_management.entites.User;
import com.visiontech.school_management.entites.dtos.SignUpDto;
import com.visiontech.school_management.services.interfaces.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	

	/*
	 * @GetMapping("/") public String home(Model model, Principal principal) { User
	 * user=userService.getUser(principal.getName());
	 * System.err.println(user.toString()); model.addAttribute("user", user); return
	 * "home"; }
	 */
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/registeration")
	public String registeration() {
	    return "register";
	}

	@GetMapping("/login")
	public String login() {
		return "login-page";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "login-page";
	}

	@GetMapping("/register")
	public String register(@ModelAttribute("siginup") SignUpDto user) {
		return "register-page";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("siginup") SignUpDto user, Model model) {
		
		boolean flag=userService.signUpUser(user);
		if (flag) {
			model.addAttribute("msg", "Registered Sucessfully..!");
			return "login-page";
		}
		else {
			
			model.addAttribute("msg", "User already exists");
		}
		return "register-page";
	}
}
