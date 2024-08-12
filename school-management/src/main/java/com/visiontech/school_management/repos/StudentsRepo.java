package com.visiontech.school_management.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.visiontech.school_management.entites.Students;

public interface StudentsRepo extends JpaRepository<Students, Long> {

	@Query("select s from Students s where cast(s.rollNo as string) like concat('%',:query,'%')"+
			"or s.firstName  like concat('%',:query,'%')"+
			"or s.lastName like concat('%',:query,'%')"+
			"or s.email like concat('%',:query,'%')"+
			 "or (:query = 'male' AND s.gender = 'male')" +
		     "or (:query = 'female' AND s.gender = 'female')")
	List<Students> searchStudent(String query);
	
	
	Optional<Students> findByRollNo(Long id);
	
	void deleteByRollNo(Long rollNo);
	
	
}
