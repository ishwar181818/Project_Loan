package com.man.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
	
	public int statuscode;
	
	public String msg;
	
	public Date date;
	
	public String url;
	
	public HttpStatus errorMessage;

}
