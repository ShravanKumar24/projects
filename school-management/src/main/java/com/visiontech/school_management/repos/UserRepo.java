package com.visiontech.school_management.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.visiontech.school_management.entites.Role;
import com.visiontech.school_management.entites.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	User findByRole(Role role);

}
