package com.visiontech.school_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.visiontech.school_management.entites.Role;
import com.visiontech.school_management.entites.User;
import com.visiontech.school_management.entites.dtos.SignUpDto;
import com.visiontech.school_management.repos.UserRepo;
import com.visiontech.school_management.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public boolean signUpUser(SignUpDto req) {

		User findUser = userRepo.findByEmail(req.getEmail()).orElse(null);
		if (findUser == null) {
			User user = new User();
			user.setFirstName(req.getFirstName());
			user.setLastName(req.getLastName());
			user.setEmail(req.getEmail());
			user.setPassword(passwordEncoder.encode(req.getPassword()));
			user.setRole(Role.USER);
			userRepo.save(user);
			return true;
		}
		return false;
	}

	@Override
	public User getUser(String email) {
		User user = userRepo.findByEmail(email).get();
		return user;
	}

}
