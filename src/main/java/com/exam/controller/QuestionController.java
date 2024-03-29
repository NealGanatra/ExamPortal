package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entities.exam.Question;
import com.exam.entities.exam.Quiz;
import com.exam.services.QuestionService;
import com.exam.services.QuizService;



@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<?> add(@RequestBody Question question){
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}
	
	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody Question question){
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getQuestions(){
		return ResponseEntity.ok(this.questionService.getQuestions());
	}
	
	@GetMapping("/{quesid}")
	public ResponseEntity<?> getQuestion(@PathVariable("quesid") Long qid){
		return ResponseEntity.ok(this.questionService.getQuestion(qid));
	}
	
	@DeleteMapping("/{quesid}")
	public void delete(@PathVariable("quesid") Long qid){
		this.questionService.deleteQuestion(qid);
	}
	
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid){
		/*
		 * Quiz quiz=new Quiz(); quiz.setQid(qid);
		 */
		Quiz quiz=this.quizService.getQuiz(qid);
		Set<Question> questions=quiz.getQuestions();
		List list=new ArrayList(questions);
		if(list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			list=list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()+1));
		}
		Collections.shuffle(list);
		
		return ResponseEntity.ok(this.questionService.getQuestionsOfQuiz(quiz));
	}
}
