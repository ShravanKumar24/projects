package com.quiz.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.quiz.entites.Questions;
import com.quiz.repos.QuestionsRepo;
import com.quiz.services.interfaces.QuestionService;

@Service
public class QuestionsServicesImpl implements QuestionService {

	@Autowired
	QuestionsRepo questionsRepo;

	@Override
	public ResponseEntity<?> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionsRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> getQuestionsById(long id) {
		try {
			if (id > 0 && questionsRepo.findById(id).get() != null) {
				return new ResponseEntity<>(questionsRepo.findById(id).get(), HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> updateQuestion(long id, Questions questions) {

		try {
			Questions ques = questionsRepo.findById(id).get();
			if (ques.getId() == questions.getId()) {
				ques.setCategory(questions.getCategory());
				ques.setDifficultylevel(questions.getDifficultylevel());
				ques.setOption1(questions.getOption1());
				ques.setOption2(questions.getOption2());
				ques.setOption3(questions.getOption3());
				ques.setOption4(questions.getOption4());
				ques.setQuestionTitle(questions.getQuestionTitle());
				ques.setRightAnswer(questions.getRightAnswer());
				return new ResponseEntity<>(questionsRepo.save(ques), HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);

	}

	@Override
	public ResponseEntity<?> createQuestion(Questions questions) {

		try {
			return new ResponseEntity<>(questionsRepo.save(questions), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> deleteQuestion(long id) {
		try {
			if (id > 0 && questionsRepo.findById(id).get() != null) {
				questionsRepo.deleteById(id);
				return new ResponseEntity<>("Sucessfuly Deleted", HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> findByCategory(String category) {

		try {
			if (category != null && category != "" && questionsRepo.findByCategory(category) != null) {
				return new ResponseEntity<>(questionsRepo.findByCategory(category), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> findByDifficultylevel(String difficultylevel) {
		try {
			if (difficultylevel != null && difficultylevel != ""
					&& questionsRepo.findByDifficultylevel(difficultylevel) != null) {
				return new ResponseEntity<>(questionsRepo.findByDifficultylevel(difficultylevel), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);

	}

}
