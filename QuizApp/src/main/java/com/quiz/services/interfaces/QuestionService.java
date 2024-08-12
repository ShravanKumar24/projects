package com.quiz.services.interfaces;

import org.springframework.http.ResponseEntity;
import com.quiz.entites.Questions;

public interface QuestionService {

		ResponseEntity<?> getAllQuestions();
		ResponseEntity<?>  getQuestionsById(long id);
		ResponseEntity<?>  updateQuestion(long id, Questions questions);
		ResponseEntity<?>  createQuestion(Questions questions);
		ResponseEntity<?>  deleteQuestion(long id);
		ResponseEntity<?>  findByCategory(String category);
		ResponseEntity<?>  findByDifficultylevel(String difficultylevel);
}
