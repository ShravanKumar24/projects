package com.quiz.services.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.quiz.entites.Questions;
import com.quiz.entites.Responses;

public interface QuestionService {

		ResponseEntity<?> getAllQuestions();
		ResponseEntity<?>  getQuestionsById(long id);
		ResponseEntity<?>  updateQuestion(long id, Questions questions);
		ResponseEntity<?>  createQuestion(Questions questions);
		ResponseEntity<?>  deleteQuestion(long id);
		ResponseEntity<?>  findByCategory(String category);
		ResponseEntity<?>  findByDifficultylevel(String difficultylevel);
		ResponseEntity<List<Long>> generateQuestions(String category, int numQuestions);
		ResponseEntity<?> getGenerateQuestions(List<Long> questionsId);
		ResponseEntity<Integer> getResult(List<Responses> responses);
}
