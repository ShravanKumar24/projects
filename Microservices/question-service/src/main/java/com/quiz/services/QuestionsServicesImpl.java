package com.quiz.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.quiz.entites.Questions;
import com.quiz.entites.QuestionsWrapper;
import com.quiz.entites.Responses;
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

	@Override
	public ResponseEntity<List<Long>> generateQuestions(String category, int numQuestions) {
		List<Long> quesIds = questionsRepo.findRandomQuestions(category, numQuestions);
		return new ResponseEntity<>(quesIds, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getGenerateQuestions(List<Long> questionsId) {
		List<QuestionsWrapper> wrappers = new ArrayList<>();
		List<Questions> questions = new ArrayList<>();
		for (Long id : questionsId) {
			questions.add(questionsRepo.findById(id).get());
		}
		for (Questions ques : questions) {
			wrappers.add(new QuestionsWrapper(ques.getId(), ques.getQuestionTitle(), ques.getOption1(),
					ques.getOption2(), ques.getOption3(), ques.getOption4()));
		}

		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> getResult(List<Responses> responses) {
		int result = 0;
		for (Responses response : responses) {
			Questions question = questionsRepo.findById(response.getId()).get();
			if (response.getResponse().equals(question.getRightAnswer())) {
				result++;
			}
		}

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
