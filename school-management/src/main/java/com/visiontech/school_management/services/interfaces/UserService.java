package com.visiontech.school_management.services.interfaces;

import com.visiontech.school_management.entites.User;
import com.visiontech.school_management.entites.dtos.SignUpDto;

public interface UserService{
	
	

	boolean signUpUser(SignUpDto req);
	User getUser(String email);
	

}
