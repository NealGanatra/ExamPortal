package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entities.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>{

}
