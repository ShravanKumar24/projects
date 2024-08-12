package com.visiontech.school_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.visiontech.school_management.entites.Role;
import com.visiontech.school_management.entites.Students;
import com.visiontech.school_management.entites.Teachers;
import com.visiontech.school_management.entites.User;
import com.visiontech.school_management.repos.StudentsRepo;
import com.visiontech.school_management.repos.TeachersRepo;
import com.visiontech.school_management.repos.UserRepo;

@SpringBootApplication
public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@Autowired
	private StudentsRepo stdRepo;

	@Autowired
	private TeachersRepo teacherRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	



}
