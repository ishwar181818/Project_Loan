 package com.cjc.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	@ExceptionHandler(MailidInvalidException.class)
	public ResponseEntity<ApiResponse> MailidInvalidExceptionHandler(MailidInvalidException e,HttpServletRequest request) {
		
		
		System.out.println("Handle");
		
		ApiResponse error = new ApiResponse();
		
		error.setDate(new Date());
		
		error.setMsg(e.getMessage());
		
		error.setStatuscode(HttpStatus.NOT_FOUND.value());
		
		error.setErrorMessage(HttpStatus.NOT_FOUND);
		error.setUrl(request.getRequestURI());
		
		
		return new ResponseEntity<ApiResponse>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MobileNoInvalidException.class)
	public ResponseEntity<ApiResponse> MobileNoInvalidExceptionHandler(MobileNoInvalidException e,HttpServletRequest request) {
		
		
		System.out.println("Handle");
		
		ApiResponse error = new ApiResponse();
		
		error.setDate(new Date());
		
		error.setMsg(e.getMessage());
		
		error.setStatuscode(HttpStatus.NOT_FOUND.value());
		
		error.setErrorMessage(HttpStatus.NOT_FOUND);
		error.setUrl(request.getRequestURI());
		
		
		return new ResponseEntity<ApiResponse>(error,HttpStatus.NOT_FOUND);
	}
	
	
	

}
