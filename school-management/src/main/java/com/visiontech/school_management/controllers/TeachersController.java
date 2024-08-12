package com.visiontech.school_management.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.visiontech.school_management.entites.Teachers;
import com.visiontech.school_management.entites.User;
import com.visiontech.school_management.entites.dtos.TeachersDTO;
import com.visiontech.school_management.services.interfaces.TeacherService;
import com.visiontech.school_management.services.interfaces.UserService;

@Controller
@RequestMapping("/teacher")
public class TeachersController {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String getAllStudents(Model model, Principal pricipal) {
		List<Teachers> teachers = teacherService.getAllTeachers();
		User user = userService.getUser(pricipal.getName());
		model.addAttribute("teachers", teachers);
		model.addAttribute("user", user);
		return "teacher";
	}

	@GetMapping("/teacher-register")
	public String studentRegister(Model model) {
		TeachersDTO teacherdto = new TeachersDTO();
		model.addAttribute("teacherdto", teacherdto);
		return "teacher-register";
	}

	@GetMapping("/{tid}")
	public String getStudentByID(@PathVariable long tid, Model model, Principal pricipal) throws Exception {
		Teachers teachers = teacherService.getTeacherByTid(tid).get();
		User user = userService.getUser(pricipal.getName());
		model.addAttribute("user", user);
		return "teacher";
	}

	@GetMapping("/search")
	public String searchTeacher(@RequestParam(name = "query", required = false) String query, Model model, Principal pricipal)
			throws Exception {
		if (query == null || query.isEmpty()) {
			// If 'rollNo' is null, redirect to the student listing page
			return "redirect:/teacher";
		}
		// Continue with the search logic if 'rollNo' is provided
		List<Teachers> teacher=teacherService.searchTeachers(query);
		model.addAttribute("searchResult", teacher);
		User user = userService.getUser(pricipal.getName());
		model.addAttribute("user", user);
		return "teacher";
	}

	@PostMapping("/create")
	public String createStudent(@ModelAttribute("teacherdto") TeachersDTO teacherdto, RedirectAttributes ra) {
		teacherService.createTeacher(teacherdto);
		ra.addFlashAttribute("message", "Created Successfully");
		return "redirect:/teacher";
	}

	@GetMapping("/update/{tid}")
	public String update(@PathVariable Long tid, Model model) throws Exception {
		TeachersDTO teacherdto = teacherService.updateTeacher(tid);
		model.addAttribute("teacherdto", teacherdto);
		return "update-teacher";
	}

	@PostMapping("/edit/{tid}")
	public String updateStudent(@PathVariable Long tid, @ModelAttribute("teacherdto") TeachersDTO teacherdto,
			Model model, RedirectAttributes ra) {
		teacherService.editTeacher(tid, teacherdto);
		ra.addFlashAttribute("message", "Updated Successfully");
		return "redirect:/teacher";
	}

	@GetMapping("/delete/{tid}")
	public String deleteStudent(@PathVariable Long tid, RedirectAttributes ra) {
		teacherService.deleteTeacherByTid(tid);
		ra.addFlashAttribute("message", "Deleted Successfully");
		return "redirect:/teacher";
	}
}
