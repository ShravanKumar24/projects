package com.visiontech.school_management.repos;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.visiontech.school_management.entites.Teachers;


public interface TeachersRepo extends JpaRepository<Teachers, Long> {
	
	@Query("select t from Teachers t where cast(t.tid as string) like concat('%',:query,'%')"+
			"or t.firstName  like concat('%',:query,'%')"+
			"or t.lastName like concat('%',:query,'%')"+
			"or t.email like concat('%',:query,'%')"+
			"or cast(t.mobileNumber as string) like concat('%',:query,'%')"+
			"or (:query = 'male' AND t.gender = 'male')" +
		    "or (:query = 'female' AND t.gender = 'female')")
	List<Teachers> searchTeacher(String query);

	Optional<Teachers> findByTid(Long tid);

	void deleteByTid(Long tid);
	
	
}
