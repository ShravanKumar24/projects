package com.visiontech.school_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.visiontech.school_management.entites.CustomUser;
import com.visiontech.school_management.entites.User;
import com.visiontech.school_management.repos.UserRepo;


@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("No customer found with the given email."));
		return new CustomUser(user);
	}

}
