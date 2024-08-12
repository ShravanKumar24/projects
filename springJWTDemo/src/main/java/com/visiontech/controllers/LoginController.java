package com.visiontech.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visiontech.model.ChangePassword;
import com.visiontech.services.LoginService;

@RestController
@RequestMapping("/api/user")
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@PutMapping("/update-password")
	public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword, Principal currentUser) throws Exception {
		return new ResponseEntity<>(loginService.changePassword(changePassword,currentUser),HttpStatus.ACCEPTED);
	}
}
