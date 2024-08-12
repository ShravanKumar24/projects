package com.quiz.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.entites.Responses;

@FeignClient("QUESTION-SERVICE")
public interface QuizInf {

	@GetMapping("/question/generate")
	public ResponseEntity<List<Long>> generateQuestions(@RequestParam String category, @RequestParam int numquestions);

	@PostMapping("/question/getquestions")
	public ResponseEntity<?> getGenerateQuestions(@RequestBody List<Long> questionsId);

	@PostMapping("/question/getresult")
	public ResponseEntity<Integer> getResult(@RequestBody List<Responses> responses);
}
