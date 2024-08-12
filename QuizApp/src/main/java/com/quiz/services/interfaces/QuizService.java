package com.quiz.services.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.quiz.entites.Responses;

public interface QuizService {

	ResponseEntity<String> createQuiz(String category, int numQ, String title);

	ResponseEntity<?> getQuizQuestions(long id);

	ResponseEntity<?> getResults(long id, List<Responses> responses);

	
}
