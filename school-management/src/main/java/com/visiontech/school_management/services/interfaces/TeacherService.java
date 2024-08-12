package com.visiontech.school_management.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.visiontech.school_management.entites.Teachers;
import com.visiontech.school_management.entites.dtos.TeachersDTO;

public interface TeacherService {

	List<Teachers> getAllTeachers();

	Optional<Teachers> getTeacherByTid(Long tid) throws Exception;

	List<Teachers> searchTeachers(String query);
		
	Teachers createTeacher(TeachersDTO teacherDto);

	Teachers editTeacher(Long tid, TeachersDTO teacherDto);

	TeachersDTO updateTeacher(Long tid);
	
	String deleteTeacherByTid(Long rollNo);
}
