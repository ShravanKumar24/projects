package com.visiontech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visiontech.DTOS.AuthenticationResponse;
import com.visiontech.DTOS.SignInDto;
import com.visiontech.DTOS.SignupDto;
import com.visiontech.model.User;
import com.visiontech.services.LoginService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/signup")
	public ResponseEntity<User> createSignUp(@RequestBody SignupDto signUp) throws Exception {
		return new ResponseEntity<>(loginService.createUser(signUp), HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthenticationResponse> signIn(@RequestBody SignInDto signInReq) throws Exception {
		return new ResponseEntity<>(loginService.signInUser(signInReq), HttpStatus.OK);
	}
}
