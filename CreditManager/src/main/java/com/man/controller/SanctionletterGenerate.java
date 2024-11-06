package com.man.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.man.model.Creditlimit;
import com.man.model.LoanApplication;
import com.man.model.SanctionLetter;
import com.man.servicei.ServiceI;
@RestController
@RequestMapping("/hm")
public class SanctionletterGenerate {
	
	
	@Autowired
	ServiceI ss;
	
	@PutMapping("/generatePdf/{creditid}")
	public Creditlimit generateSanctionLetter(@PathVariable int creditid , @RequestBody SanctionLetter sanctionLetter)
	
	{
		
		
		
		return ss.generateSactionletter(creditid, sanctionLetter);
		
		
		
	}
	
	@GetMapping("/sendSantionLetterMail/{customerId}")
	public String sendSanctionLetterMail(@PathVariable("customerId") Integer customerId)
	{
		System.out.println("Mail sending started");
		
		
		ss.getCustomerData(customerId);
		
		
		return "mail sent";
		
	}
	
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	


