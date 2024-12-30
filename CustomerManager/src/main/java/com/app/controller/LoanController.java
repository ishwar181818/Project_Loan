package com.app.controller;

import java.util.Arrays;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.Enquiry;
import com.app.model.LoanApplication;
import com.app.servicei.CustomerloanInterface;

@RestController
@RequestMapping("/lo")
@CrossOrigin(origins = "http://localhost:3000") 
public class LoanController {
	
	@Autowired
	CustomerloanInterface esi;
	
	@Autowired
	RestTemplate rt;
	
	private String enquirystatus="Approved";
	
	@Value("${server.port}")
	String port;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);
	
	@PostMapping("/loan")
	public ResponseEntity<String> addEmployee(@RequestPart("info") String json,
			                                   @RequestPart("addressproof") MultipartFile addressproof,
			                                  @RequestPart("pan") MultipartFile pancard,
			                                  @RequestPart("incometax") MultipartFile incometax,
			                                  @RequestPart("addhar")MultipartFile adharcard,
			                                  
			                                  @RequestPart("photo")MultipartFile photo,
			                                  
			                                  @RequestPart("sign")MultipartFile signature,
			                                  @RequestPart("cheque")MultipartFile bankCheque, 
	                                          @RequestPart ("slip")MultipartFile salaryslip
			                                  )
	
	
	
	
	
	{
		
		////esi.saveEmplyeeData(json,pancard,aadharcard);
		
		LOGGER.info("Posting Loan application ");
		
		String url = "http://localhost:8082/cm/get/"+ enquirystatus;
		
		Enquiry[]enq=rt.getForObject(url, Enquiry[].class);
		
		List<Enquiry>l = Arrays.asList(enq);
		
		System.out.println(l);
		System.out.println(json);
		System.out.println(pancard);
		System.out.println("********");
	    System.out.println(adharcard);
		
		esi.saveCustomerData(json,addressproof, pancard,incometax, adharcard,photo,signature,bankCheque,salaryslip,l);
		
	
		
		return new ResponseEntity<String>("Data Saved", HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<LoanApplication>> getAllLoanSubmittedDetails()
	
	{
		
		LOGGER.info("Getting All data of Loan application ");
		
		List<LoanApplication>list=esi.getAllLoanSubmittedDetails();
		
		System.out.println("port Executing is"+":"+port);
		
		
		return new ResponseEntity<List<LoanApplication>>(list,HttpStatus.OK) ;
	}
	
	
	@DeleteMapping("/del/{customerid}")
	public ResponseEntity<String> deleteSingleData(@PathVariable int customerid)
	
	
	
	{
		LOGGER.info("deleting Single data of  Loan application ");
		esi.deleteCustomerLoanData(customerid);
		
		
		return new ResponseEntity<String>("data has been deleted for "+":"+customerid,HttpStatus.NO_CONTENT);
		
	}
	
	@GetMapping("/getsingledata/{customerid}")
	public ResponseEntity<LoanApplication> getSingleLoanData(@PathVariable int customerid )
	
	{
		
		
		LOGGER.info("Getting single data of  Loan application ");
		LoanApplication loan=esi.getSingleLoanData(customerid);
		
		
		 
		
		
		return new ResponseEntity<LoanApplication>(loan,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delAll")
	public ResponseEntity<String> deleteAllData()
	
	{
		LOGGER.info("Deleting All Data of Loan Application ");
		esi.deleteAllData();
		
	return new ResponseEntity<String>("All data has been deleted",HttpStatus.OK);
		
		
		
	}	
	
	@PutMapping("/loan/{customerid}")
	public ResponseEntity<String> updateLoanApplication(@PathVariable("customerid") int customerid, 
	                                                     @RequestPart("info") String json,
	                                                     @RequestPart("addressproof") MultipartFile addressproof,
	                                                     @RequestPart("pan") MultipartFile pancard,
	                                                     @RequestPart("incometax") MultipartFile incometax,
	                                                     @RequestPart("addhar") MultipartFile adharcard,
	                                                     @RequestPart("photo") MultipartFile photo,
	                                                     @RequestPart("sign") MultipartFile signature,
	                                                     @RequestPart("cheque") MultipartFile bankCheque, 
	                                                     @RequestPart("slip") MultipartFile salaryslip) {
	    LOGGER.info("Updating Loan application for Customer ID: {}", customerid);
	    
	    // Fetch the existing loan application by customer ID
	    String url = "http://localhost:8082/cm/get/" + enquirystatus;
	    Enquiry[] enq = rt.getForObject(url, Enquiry[].class);
	    
	    List<Enquiry> l = Arrays.asList(enq);
	    
	    LOGGER.info("Enquiry details: {}", l);
	    LOGGER.info("Loan application data: {}", json);
	    
	    esi.updateCustomerData(customerid, json, addressproof, pancard, incometax, adharcard, photo, signature, bankCheque, salaryslip, l);
	    
	    return new ResponseEntity<String>("Loan application updated", HttpStatus.OK);
	}

	
	
	
	
	

}
