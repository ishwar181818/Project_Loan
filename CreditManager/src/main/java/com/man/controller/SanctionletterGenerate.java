package com.man.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	public static final Logger LOGGER = LoggerFactory.getLogger(SanctionletterGenerate.class);
	
	@PutMapping("/generatePdf/{creditid}")
	public Creditlimit generateSanctionLetter(@PathVariable int creditid , @RequestBody SanctionLetter sanctionLetter)
	
	{
		
		LOGGER.info("Generating Sanction letter and Saving it into the Sanction letter Database");
		
		return ss.generateSactionletter(creditid, sanctionLetter);
		
		
		
	}
	
	@GetMapping("/sendSantionLetterMail/{customerId}")
	public String sendSanctionLetterMail(@PathVariable("customerId") Integer customerId)
	{
		System.out.println("Mail sending started");
		
		
		LOGGER.info("Sending Sanctioned Loan Mail(Sanction Letter Quotation) To the Customer ");
		
		ss.getCustomerData(customerId);
		
		
		return "mail sent";
		
	}
	@PutMapping("/set/{customerid}/{loanstatus}")
	public String statussanctioned(@PathVariable int customerid,@PathVariable String loanstatus)
	{
		
		
		ss.setloanstatus(customerid ,loanstatus);
		
		
		
		
		return "Loan Status Updated to Sanctioned";
		
	}
	
	@PutMapping("/generate/{customerid}")
	public Creditlimit generateLetter(@PathVariable int customerid,@RequestBody SanctionLetter sanctionLetter)
	
	{
		
		
		
		int creditid = customerid;	
		
		
		return generateSanctionLetter(creditid,sanctionLetter);
		
		
	}
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	


