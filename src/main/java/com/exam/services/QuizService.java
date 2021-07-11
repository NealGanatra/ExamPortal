package com.exam.services;

import java.util.Set;

import com.exam.entities.exam.Quiz;

public interface QuizService {

	public Quiz addQuiz(Quiz quiz);
	
	public Quiz updateQuiz(Quiz quiz);
	
	public Set<Quiz> getQuizzes();
	
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long quizId);
	
	
}
