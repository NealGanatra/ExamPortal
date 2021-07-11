package com.exam.exceptions;

import java.util.Date;

import javax.servlet.annotation.HandlesTypes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.exam.entities.ErrorDetails;

@ControllerAdvice
public class MyException {
	
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<?> examException(ArithmeticException e,WebRequest request) {
		System.out.println("Inside MyException");
	ErrorDetails error=new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
	return new ResponseEntity(error, HttpStatus.BAD_GATEWAY);

}
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> userFounException(UserFoundException e,WebRequest request){
		System.out.println("Inside Handler UserFoundException");
		ErrorDetails error=new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity(error,HttpStatus.ALREADY_REPORTED);
	}
}
