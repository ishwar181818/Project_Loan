package com.org.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.org.model.Cibil;


@RestController
public class CibilScoreController {
	
	
	@GetMapping("/generate")
	public Cibil generatescore()
	
	{
		
		Cibil cb =new Cibil();
		
		Random rm = new Random();
		
		int no= rm.nextInt(300, 900);
		
		
		
		cb.setCibilscore(no);
		
		
		return cb;
		
		
	}
	
	
	
	
	
	
	
	
	

}
