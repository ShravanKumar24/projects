package com.visiontech.school_management.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.visiontech.school_management.entites.Students;
import com.visiontech.school_management.entites.dtos.StudentsDTO;

public interface StudentsService {

	List<Students> getAllStudents();
	
	Optional<Students> getStudentByRollNo(Long query) throws Exception;

	List<Students> searchStudent(String query) throws Exception;

	Students createStudent(StudentsDTO stdDto);

	Students editStudent(Long rollNo, StudentsDTO stdDto);

	StudentsDTO updateStudent(Long rollNo);
	
	String deleteStudentByRollNo(Long rollNo);

}
