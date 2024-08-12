package com.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.entites.Questions;
import com.quiz.services.interfaces.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionsController {

	@Autowired
	private QuestionService questionService;

	@GetMapping("/allquestions")
	public ResponseEntity<?> getAllQuestions() {
		return questionService.getAllQuestions();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getQuestionByID(@PathVariable("id") long id) {
		return questionService.getQuestionsById(id);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createQuestions(@RequestBody Questions questions) {
		return questionService.createQuestion(questions);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> createQuestions(@PathVariable("id") long id, @RequestBody Questions questions) {
		return questionService.updateQuestion(id, questions);
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<?> findByCategory(@PathVariable("category") String category) {
		return questionService.findByCategory(category);
	}

	@GetMapping("/difficulty/{level}")
	public ResponseEntity<?> findByLevel(@PathVariable("level") String difficultylevel) {
		return questionService.findByDifficultylevel(difficultylevel);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteQuestion(@PathVariable("id") long id) {
		return questionService.deleteQuestion(id);
	}

}
