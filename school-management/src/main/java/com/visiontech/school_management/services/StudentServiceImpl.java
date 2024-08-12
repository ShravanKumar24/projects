package com.visiontech.school_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visiontech.school_management.entites.Students;
import com.visiontech.school_management.entites.dtos.StudentsDTO;
import com.visiontech.school_management.repos.StudentsRepo;
import com.visiontech.school_management.services.interfaces.StudentsService;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentsService {

	@Autowired
	private StudentsRepo studentsRepo;

	@Override
	public List<Students> getAllStudents() {
		return studentsRepo.findAll();
	}

	@Override
	public List<Students> searchStudent(String query) throws Exception {
		return studentsRepo.searchStudent(query);
	}

	@Override
	public Students createStudent(StudentsDTO stdDto) {
		Students std = new Students();
		std.setRollNo(stdDto.getRollNo());
		std.setFirstName(stdDto.getFirstName().toLowerCase());
		std.setLastName(stdDto.getLastName().toLowerCase());
		std.setEmail(stdDto.getEmail());
		std.setGender(stdDto.getGender().toLowerCase());
		return studentsRepo.save(std);
	}

	@Override
	public Students editStudent(Long rollNo,StudentsDTO stdDto){
		Students std = studentsRepo.findByRollNo(rollNo).get();
		if (std != null) {
			std.setRollNo(stdDto.getRollNo());
			std.setFirstName(stdDto.getFirstName().toLowerCase());
			std.setLastName(stdDto.getLastName().toLowerCase());
			std.setEmail(stdDto.getEmail());
			std.setGender(stdDto.getGender().toLowerCase());

			return studentsRepo.save(std);
		}
		return null;
	}
	
	@Override
	public StudentsDTO updateStudent(Long rollNo) {
		Students std = studentsRepo.findByRollNo(rollNo).get();
		StudentsDTO stddto=new StudentsDTO();
		stddto.setRollNo(std.getRollNo());
		stddto.setFirstName(std.getFirstName().toLowerCase());
		stddto.setLastName(std.getLastName().toLowerCase());
		stddto.setGender(std.getGender().toLowerCase());
		stddto.setEmail(std.getEmail());
		return stddto;
	}

	@Override
	@Transactional
	public String deleteStudentByRollNo(Long rollNo) {
		studentsRepo.deleteByRollNo(rollNo);
		return "Sucessfully Deleted";
	}

	@Override
	public Optional<Students> getStudentByRollNo(Long rollNo) throws Exception {
		
		return studentsRepo.findByRollNo(rollNo);
	}

}
