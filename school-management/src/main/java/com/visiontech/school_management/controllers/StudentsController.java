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
import com.visiontech.school_management.entites.Students;
import com.visiontech.school_management.entites.User;
import com.visiontech.school_management.entites.dtos.StudentsDTO;
import com.visiontech.school_management.services.interfaces.StudentsService;
import com.visiontech.school_management.services.interfaces.UserService;

@Controller
@RequestMapping("/student")
public class StudentsController {

	@Autowired
	private StudentsService studentsService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String getAllStudents(Model model, Principal pricipal) {
		User user = userService.getUser(pricipal.getName());
		List<Students> std = studentsService.getAllStudents();
		model.addAttribute("students", std);
		model.addAttribute("user", user);
		return "student";
	}

	@GetMapping("/student-register")
	public String studentRegister(Model model) {
		StudentsDTO stddto = new StudentsDTO();
		model.addAttribute("stddto", stddto);
		return "student-register";
	}

	@GetMapping("/{rollNo}")
	public String getStudentByID(@PathVariable long rollNo, Model model, Principal pricipal) throws Exception {
		User user = userService.getUser(pricipal.getName());
		model.addAttribute("user", user);
		Students student = studentsService.getStudentByRollNo(rollNo).get();
		return "student";
	}

	@GetMapping("/search")
	public String searchStudent(@RequestParam(name = "query", required = false) String query, Model model,
			Principal pricipal) throws Exception {
		 if (query == null || query.isEmpty()) {
		        // If query is null or empty, redirect to the student listing page
		        return "redirect:/student";
		    }

		User user = userService.getUser(pricipal.getName());
		model.addAttribute("user", user);
		List<Students> students = studentsService.searchStudent(query);
	    model.addAttribute("searchResult", students);
	    return "student";
	}

	@PostMapping("/create")
	public String createStudent(@ModelAttribute("stddto") StudentsDTO stddto, RedirectAttributes ra) {
		studentsService.createStudent(stddto);
		ra.addFlashAttribute("message", "Added Successfully");
		return "redirect:/student";
	}

	@GetMapping("/update/{rollNo}")
	public String update(@PathVariable Long rollNo, Model model) throws Exception {
		StudentsDTO sttdto = studentsService.updateStudent(rollNo);
		model.addAttribute("stddto", sttdto);
		return "update-student";
	}

	@PostMapping("/edit/{rollNo}")
	public String updateStudent(@PathVariable Long rollNo, @ModelAttribute("stddto") StudentsDTO stddto, Model model,
			RedirectAttributes ra) {
		studentsService.editStudent(rollNo, stddto);
		ra.addFlashAttribute("message", "Updated Successfully");
		return "redirect:/student";
	}

	@GetMapping("/delete/{rollNo}")
	public String deleteStudent(@PathVariable Long rollNo, RedirectAttributes ra) {
		studentsService.deleteStudentByRollNo(rollNo);
		ra.addFlashAttribute("message", "Deleted Successfully");
		return "redirect:/student";
	}

}
