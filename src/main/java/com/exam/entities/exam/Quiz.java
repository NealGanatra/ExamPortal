package com.exam.entities.exam;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Quiz {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long qid;

private String title;

private String maxMarks;

private String description;

private String numberOfQuestions;

private boolean active=false;

@ManyToOne(fetch = FetchType.EAGER)
private Category category;

@OneToMany(fetch = FetchType.LAZY,mappedBy = "quiz",cascade = CascadeType.ALL)
@JsonIgnore
private Set<Question> questions=new HashSet<>();



public Set<Question> getQuestions() {
	return questions;
}

public void setQuestions(Set<Question> questions) {
	this.questions = questions;
}

public Quiz() {
	super();
	// TODO Auto-generated constructor stub
}

public Quiz(Long qid, String title, String maxMarks, String description, String numberOfQuestions, boolean active) {
	super();
	this.qid = qid;
	this.title = title;
	this.maxMarks = maxMarks;
	this.description = description;
	this.numberOfQuestions = numberOfQuestions;
	this.active = active;
}

public Long getQid() {
	return qid;
}

public void setQid(Long qid) {
	this.qid = qid;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getMaxMarks() {
	return maxMarks;
}

public void setMaxMarks(String maxMarks) {
	this.maxMarks = maxMarks;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getNumberOfQuestions() {
	return numberOfQuestions;
}

public void setNumberOfQuestions(String numberOfQuestions) {
	this.numberOfQuestions = numberOfQuestions;
}

public boolean isActive() {
	return active;
}

public void setActive(boolean active) {
	this.active = active;
}

public Category getCategory() {
	return category;
}

public void setCategory(Category category) {
	this.category = category;
}


}
