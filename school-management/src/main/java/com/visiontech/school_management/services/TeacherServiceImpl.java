package com.visiontech.school_management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.visiontech.school_management.entites.Teachers;
import com.visiontech.school_management.entites.dtos.TeachersDTO;
import com.visiontech.school_management.repos.TeachersRepo;
import com.visiontech.school_management.services.interfaces.TeacherService;

import jakarta.transaction.Transactional;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeachersRepo teachersRepo;

	@Override
	public List<Teachers> getAllTeachers() {
		return teachersRepo.findAll();
	}

	@Override
	public Optional<Teachers> getTeacherByTid(Long tid) throws Exception {
		Optional<Teachers> teachers = teachersRepo.findByTid(tid);
		return teachers;
	}

	@Override
	public Teachers createTeacher(TeachersDTO teacherDto) {
		Teachers teachers = new Teachers();
		teachers.setTid(teacherDto.getTid());
		teachers.setFirstName(teacherDto.getFirstName().toLowerCase());
		teachers.setLastName(teacherDto.getLastName().toLowerCase());
		teachers.setGender(teacherDto.getGender().toLowerCase());
		teachers.setMobileNumber(teacherDto.getMobileNumber());
		teachers.setEmail(teacherDto.getEmail());
		return teachersRepo.save(teachers);
	}

	@Override
	public Teachers editTeacher(Long tid, TeachersDTO teacherDto) {
		Teachers teachers = teachersRepo.findByTid(tid).get();
		if (teachers != null) {
			teachers.setTid(teacherDto.getTid());
			teachers.setFirstName(teacherDto.getFirstName().toLowerCase());
			teachers.setLastName(teacherDto.getLastName().toLowerCase());
			teachers.setGender(teacherDto.getGender().toLowerCase());
			teachers.setMobileNumber(teacherDto.getMobileNumber());
			teachers.setEmail(teacherDto.getEmail());

			return teachersRepo.save(teachers);
		}

		return null;
	}

	@Override
	public TeachersDTO updateTeacher(Long tid) {
		Teachers teachers = teachersRepo.findByTid(tid).get();

		TeachersDTO teacherDto = new TeachersDTO();

		teacherDto.setTid(teachers.getTid());
		teacherDto.setFirstName(teachers.getFirstName().toLowerCase());
		teacherDto.setLastName(teachers.getLastName().toLowerCase());
		teacherDto.setGender(teachers.getGender().toLowerCase());
		teacherDto.setMobileNumber(teachers.getMobileNumber());
		teacherDto.setEmail(teachers.getEmail());

		return teacherDto;
	}

	@Override
	@Transactional
	public String deleteTeacherByTid(Long tid) {
		teachersRepo.deleteByTid(tid);
		return "Sucessfully Deleted";
	}

	@Override
	public List<Teachers> searchTeachers(String query) {
		
		return teachersRepo.searchTeacher(query);
	}

}
