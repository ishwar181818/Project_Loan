package com.man.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.man.model.Creditlimit;
import com.man.model.LoanApplication;
import com.man.model.SanctionLetter;
import com.man.servicei.ServiceI;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/hm")
public class SanctionletterGenerate {
	
	
	@Autowired
	ServiceI ss;
	
	@Autowired
	RestTemplate rt;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(SanctionletterGenerate.class);
	
	@PutMapping("/generatePdf/{creditid}")
	public Creditlimit generateSanctionLetter(@PathVariable int creditid , @RequestBody SanctionLetter sanctionLetter)
	
	{
		
		LOGGER.info("Generating Sanction letter and Saving it into the Sanction letter Database");
		
		return ss.generateSactionletter(creditid, sanctionLetter);
		
		
		
	}
	
	@GetMapping("/sendSantionLetterMail/{customerId}")
	public ResponseEntity<String> sendSanctionLetterMail(@PathVariable("customerId") Integer customerId)
	{
		System.out.println("Mail sending started");
		
		
		LOGGER.info("Sending Sanctioned Loan Mail(Sanction Letter Quotation) To the Customer ");
		
		ss.getCustomerData(customerId);
		
		
		return new ResponseEntity<String>("mail Sent" , HttpStatus.OK);
		
	}
	@PutMapping("/set/{customerid}/{loanstatus}")
	public ResponseEntity<String> statussanctioned(@PathVariable int customerid,@PathVariable String loanstatus)
	{
		
		
		ss.setloanstatus(customerid ,loanstatus);
		
		
		
		//"Loan Status Updated to Sanctioned"
		
		
		return new ResponseEntity<String>("Loan Status Updated to Sanctioned",HttpStatus.CREATED);
		
	}
	
	@PutMapping("/generate/{customerid}")
	public Creditlimit generateLetter(@PathVariable int customerid,@RequestBody SanctionLetter sanctionLetter)
	
	{
		
		
		
		int creditid = customerid;	
		
		
		return generateSanctionLetter(creditid,sanctionLetter);
		
		
	}
	
	
	
	@GetMapping("/getAll")
	public ResponseEntity<List<LoanApplication>> getAllData()
	
	{
		
		
		 String operationExecutiveUrl = "http://localhost:8084/oe/getAllCustomer";  // URL of the OperationExecutive Microservice

	        // Make the GET request to the OperationExecutive service
	        ResponseEntity<List> response = rt.exchange(
	                operationExecutiveUrl, HttpMethod.GET, null, List.class);

	        // Check if the response is successful
	        if (response.getStatusCode() == HttpStatus.OK) {
	            return ResponseEntity.ok(response.getBody());
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    } 
	
	@GetMapping("/getAlll")
	public List<LoanApplication> getAllCustomerData()
	
	{
		
		
		List<LoanApplication>loans=ss.getAllCustomerSanctionedData();
		
		
		
		return loans;
		
	}
	
	
	
	@GetMapping("/getsanction/{customerid}")
	public ResponseEntity<byte[]> getSanctionLetter(@PathVariable int customerid)
	
	{
		
		
		LoanApplication loanApplication =ss.getSanctionLetter(customerid);
		
		if (loanApplication != null && loanApplication.getSanctionletter() != null) {
	        byte[] pdfBytes = loanApplication.getSanctionletter().getSanctionLetter();
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        headers.setContentDisposition(ContentDisposition.builder("inline")
	            .filename("sanction-letter.pdf")
	            .build());
	        
	        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	


