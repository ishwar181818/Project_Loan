 package com.app.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	@ExceptionHandler(LoanApplicationdataNotPresentException.class)
	public ResponseEntity<ApiResponse> AlreadyApprovedORRejectExceptionHandler(LoanApplicationdataNotPresentException e,HttpServletRequest request) {
		
		
		System.out.println("Handle");
		
		ApiResponse error = new ApiResponse();
		
		error.setDate(new Date());
		
		error.setMsg(e.getMessage());
		
		error.setStatuscode(HttpStatus.NOT_FOUND.value());
		
		error.setErrorMessage(HttpStatus.NOT_FOUND);
		error.setUrl(request.getRequestURI());
		
		
		return new ResponseEntity<ApiResponse>(error,HttpStatus.NOT_FOUND);
	}
	
	
	
	
	@ExceptionHandler(MobileNoNotMatching.class)
	public ResponseEntity<ApiResponse> MobileNoNotMatchingExceptionHandler(MobileNoNotMatching e,HttpServletRequest request) {
		
		
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
