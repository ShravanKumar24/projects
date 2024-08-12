package com.quiz.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quiz.entites.Questions;

public interface QuestionsRepo extends JpaRepository<Questions, Long> {

	List<Questions> findByCategory(String category);

	List<Questions> findByDifficultylevel(String difficultylevel);

	@Query(value="SELECT * FROM Questions WHERE category = :category ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
	public List<Questions> findRandomQuestions(String category, int numQ);
}
