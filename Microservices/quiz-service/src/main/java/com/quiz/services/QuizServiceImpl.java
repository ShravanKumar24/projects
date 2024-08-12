package com.quiz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.quiz.entites.QuestionsWrapper;
import com.quiz.entites.Quiz;
import com.quiz.entites.Responses;
import com.quiz.feign.QuizInf;
import com.quiz.repos.QuizRepo;
import com.quiz.services.interfaces.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	QuizRepo quizRepo;

	@Autowired
	QuizInf quizInf;

	@Override
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Long> quesIds = quizInf.generateQuestions(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(quesIds);
		quizRepo.save(quiz);

		return new ResponseEntity<>("Sucessfully Created", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getQuizQuestions(long id) {
		Quiz quiz = quizRepo.findById(id).get();
		List<Long> quesIds = quiz.getQuestionIds();
		ResponseEntity<?> questions = quizInf.getGenerateQuestions(quesIds);

		return new ResponseEntity<>(questions.getBody(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getResults(long id, List<Responses> responses) {
		Quiz quiz = quizRepo.findById(id).get();
		List<Long> quesIds=quiz.getQuestionIds();
		if(quesIds.size()==responses.size()) {
			ResponseEntity<Integer> result=quizInf.getResult(responses); 
			return new ResponseEntity<>(result.getBody(), HttpStatus.OK);
		}else {
			
			return new ResponseEntity<>("Please check the Request", HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
