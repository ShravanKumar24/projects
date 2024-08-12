package com.quiz.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quiz.entites.Quiz;


public interface QuizRepo extends JpaRepository<Quiz, Long> {

}
