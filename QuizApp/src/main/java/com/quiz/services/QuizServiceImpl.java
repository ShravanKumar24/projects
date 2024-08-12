package com.quiz.services;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.entites.Questions;
import com.quiz.entites.QuestionsWrapper;
import com.quiz.entites.Quiz;
import com.quiz.entites.Responses;
import com.quiz.repos.QuestionsRepo;
import com.quiz.repos.QuizRepo;
import com.quiz.services.interfaces.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	QuizRepo quizRepo;

	@Autowired
	QuestionsRepo questionsRepo;

	@Override
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		List<Questions> ques = questionsRepo.findRandomQuestions(category, numQ);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(ques);
		quizRepo.save(quiz);

		return new ResponseEntity<>("Sucessfully Created", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getQuizQuestions(long id) {
		Quiz q = quizRepo.findById(id).get();
		List<Questions> ques = q.getQuestions();
		List<QuestionsWrapper> quesForUser = new LinkedList<>();
		for (Questions qu : ques) {
			QuestionsWrapper qa = new QuestionsWrapper(qu.getId(), qu.getQuestionTitle(), qu.getOption1(),
					qu.getOption2(), qu.getOption3(), qu.getOption4());
			quesForUser.add(qa);
		}

		return new ResponseEntity<>(quesForUser, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getResults(long id, List<Responses> responses) {

		Quiz quiz = quizRepo.findById(id).get();
		List<Questions> questions = quiz.getQuestions();
		int result = 0;
		int i=0;
		for(Responses response:responses) {
			
			if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
				result++;
			}
			i++;
		}
		

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
