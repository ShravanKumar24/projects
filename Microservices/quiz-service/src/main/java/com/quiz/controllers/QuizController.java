package com.quiz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.entites.QuizDto;
import com.quiz.entites.Responses;
import com.quiz.services.interfaces.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {

	@Autowired
	QuizService quizService;

	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
		return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumquestions(), quizDto.getTitle());
	}

	@GetMapping("get/{id}")
	public ResponseEntity<?> getQuizQuestions(@PathVariable("id") long id) {
		return quizService.getQuizQuestions(id);
	}

	@PostMapping("submit/{id}")
	public ResponseEntity<?> getResults(@PathVariable("id") long id, @RequestBody List<Responses> responses) {
		return quizService.getResults(id, responses);
	}

}
