package com.visiontech.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.visiontech.model.User;

public interface UserRepo extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
}	
