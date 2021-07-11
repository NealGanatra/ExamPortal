package com.exam.exceptions;

public class UserFoundException extends Exception{

	public UserFoundException(String msg) {
		super(msg);
	}
	
	public UserFoundException() {
	super("User Already exists in DB");
	}
}
