package com.ope.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.ope.model.Enquiry;
import com.ope.model.LoanApplication;
import com.ope.servicei.ServiceI;

@RestController
@RequestMapping("/oe")
public class OEController {
	@Autowired
	ServiceI ssi;
	
	@Autowired
	RestTemplate rt;
	
	@Value("${server.port}")
	String port;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(OEController.class);
	
	//for Cibil & Enquiry Class Status Approved or Rejected
	@PutMapping("/put/{cid}")
	public ResponseEntity<Enquiry> updateEnquiryStatus(@PathVariable int cid)
	
	{
		LOGGER.info("Updating enquiry status which is pending  ");
		
		Enquiry e=ssi.updateEnquiryStatus(cid);
		
		
		return new ResponseEntity<Enquiry>(e,HttpStatus.OK);
		
	}
	
	@GetMapping("/get")//Calling getAll data from Customer mNagaer for Loan Application
	public ResponseEntity<List<LoanApplication>> getAllCustomerManagerLoanData()//lOAN Application Handler Method Taking alldta
	
	{                                                            //from CustomerManager
		LOGGER.info("Getting All data of Loan Application  ");
		
		String url="http://localhost:8082/lo/getAll";/////Customer Manager LoanApplication getAll Method
		                                              //Filling the LoanForm
		//String url="lb://cibilpatch/lo/getAll";
               LoanApplication[]arr=rt.getForObject(url, LoanApplication[].class);
               
               List<LoanApplication>l = Arrays.asList(arr);
               
               System.out.println("port Executing is"+":"+port);
		
		
		       return new ResponseEntity<List<LoanApplication>>(l,HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllCustomer")//Directly call all the data from database as database is same for Loan app
	 public ResponseEntity<List<LoanApplication>>getAllLoanAppliedData()
	 
	 {
		LOGGER.info("Getting All data of Loan Application  ");
		 
		List<LoanApplication>l=ssi.getAllLoanAppliedData();
		 
		System.out.println("port Executing is"+":"+port);
		 return new ResponseEntity<List<LoanApplication>>(l,HttpStatus.OK);
		 
	 }
	
	@PutMapping("/put/{customerid}/{docstatus}")////Putting status of docstatus-verified and Loan status Verifird
	public ResponseEntity<LoanApplication> LoanAndDocsVerified(@PathVariable int customerid,@PathVariable String docstatus) {
		
		
		LOGGER.info("Verification Of  Loan Application  ");
	LoanApplication loan=	ssi.LoanAndDocsVerified(customerid,docstatus);
		
		
		
	
		return new ResponseEntity<LoanApplication>(loan,HttpStatus.OK);
	
	
	}
	
	@GetMapping("/getsingle/{cid}")////Get Single data for Enquiry
	public ResponseEntity<Enquiry> getSingleDataofEnquiry(@PathVariable int cid)
	
	{
		LOGGER.info("Getting single Enquiry Data  ");
		
		Enquiry eq=ssi.getSingledata(cid);
		
		return new ResponseEntity<Enquiry>(eq,HttpStatus.OK) ;
	}
	
	@GetMapping("/getAllEnquiry")///get All Enquiry data 
	public ResponseEntity<List<Enquiry>>getAllEnquiryData()
	
	{  
		
		LOGGER.info("Getting All Enquiry Data  ");
		
		List<Enquiry>list=ssi.getAllEnquiry();
		
		
		return new ResponseEntity<List<Enquiry>>(list,HttpStatus.OK) ;
	}
	
	
	@DeleteMapping("/del/{cid}")
	public ResponseEntity<String> deletedata(@PathVariable int cid)
	{
		LOGGER.info("Deleting  single Enquiry Data  ");
		ssi.deleteSingleData(cid);
		
		//"data has been deleted for "+":"+cid;
		
		return new ResponseEntity<String>("data has been deleted for "+":"+cid,HttpStatus.NO_CONTENT);
		
	}
	
	
	@DeleteMapping("/del/{customerid}")
	public ResponseEntity<String> deleteSingleData(@PathVariable int customerid)
	
	
	
	{
		LOGGER.info("Deleting  single Loan Application  ");
		ssi.deleteCustomerLoanData(customerid);
		
		
		return new ResponseEntity<String>("data has been deleted for "+":"+customerid,HttpStatus.NO_CONTENT);
		
	}
	
	
	@GetMapping("/getsingledata/{customerid}")
	public ResponseEntity<LoanApplication> getSingleLoanData(@PathVariable int customerid )
	
	{
		LOGGER.info("Getting single Loan Application Data  ");
		LoanApplication loan=ssi.getSingleLoanData(customerid);
		
		
		 
		
		
		return new ResponseEntity<LoanApplication>(loan,HttpStatus.OK);
		
	}
	
	
	@PostMapping("/generateCreditScore/{cid}")
	public ResponseEntity<String> generateCreditScore(@PathVariable int cid) {
	    // Define the URL of Customer Manager's API
	    String customerManagerUrl = "http://localhost:8082/cm/put/" + cid;

	    try {
	        // Call Customer Manager's API to update the CIBIL score for the customer
	        ResponseEntity<String> response = rt.exchange(customerManagerUrl, HttpMethod.PUT, null, String.class);

	        if (response.getStatusCode() == HttpStatus.OK) {
	            return new ResponseEntity<>("Credit score generated and updated successfully.", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Failed to generate or update credit score.", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } catch (Exception e) {
	        // Handle any exceptions that may arise during the API call
	        return new ResponseEntity<>("Error occurred while calling Customer Manager service: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
