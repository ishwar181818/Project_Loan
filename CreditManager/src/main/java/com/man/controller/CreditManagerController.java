 package com.man.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.man.model.Enquiry;
import com.man.model.LoanApplication;
import com.man.model.SanctionLetter;
import com.man.exception.CustomerNotFoundException;
import com.man.model.Creditlimit;
import com.man.servicei.ServiceI;




@RequestMapping("/sa")
@RestController
public class CreditManagerController {
	@Autowired
	ServiceI esi;
	
	@Autowired
	RestTemplate rt;
	
	private String enquirystatus="Approved";
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CreditManagerController.class);
	
	@PostMapping("/gen/{cid}")
	public ResponseEntity<String> generateData(@PathVariable int cid)
	
	{
		LOGGER.info("Posting data Required for CreditLimit");
		
		Creditlimit cl = new Creditlimit();
		
		String url="http://localhost:8082/cm/get/"+ enquirystatus;
		
		
		
		Enquiry[]arr=rt.getForObject(url, Enquiry[].class);
		
		List<Enquiry>l = Arrays.asList(arr);
		
		
		for(Enquiry eq: l)
			
		{
			
			if(cid == eq.getCid() && eq.getEnquirystatus().equals(enquirystatus))
				
			{
				cl.setCreditid(cid);
				
				if(eq.getCb().getCibilscore() >=701 && eq.getCb().getCibilscore() <= 760)
					
				{
					
					
					
					cl.setCreditlimit(500000);
					
					
				}
				
				else if(eq.getCb().getCibilscore() >=761 && eq.getCb().getCibilscore() <= 800)
					
				{
					
					cl.setCreditlimit(700000);	
					
				}
				
              else if(eq.getCb().getCibilscore() >=801 && eq.getCb().getCibilscore() <= 900)
					
				{
					
					cl.setCreditlimit(1000000);	
					
				}
				
				
			}
			
		}
			
			List<LoanApplication>list=getAllLoanSubmitted();
			
			
			for(LoanApplication la : list)
				
			{
				
				
              if(cid == la.getCustomerid())
            	  
              {
            	  
            	  
            	  cl.setRequiredtenure(la.getRequiretenure());
            	  
              }
				
				
				
			}
			
			//Implement below Logic for Interest Rate based on the credit Limit 
		// Set interest rate based on credit limit
	    if (cl.getCreditlimit() <= 500000) {
	        cl.setInterestrate(12); // Higher interest rate for lower credit limits
	    } else if (cl.getCreditlimit() <= 700000) {
	        cl.setInterestrate(10); // Medium interest rate
	    } else if (cl.getCreditlimit() > 700000) {
	        cl.setInterestrate(8);  // Lower interest rate for higher credit limits
	    }
			
			
		
		
		
		esi.generatecreditLimit(cl,cid);
		
		return new ResponseEntity<String>("credit limit generated",HttpStatus.OK);
	}
	
	//List<LoanApplication>
		@GetMapping("/get")
		public List<LoanApplication> getAllLoanSubmitted()
		
		{
			LOGGER.info("Getting Loan Application data Filled By Customer ");
			
			List<LoanApplication>l=esi.getAllData();
			
			
			return l;
		}
		
		@PutMapping("/put/{customerid}/{status}")
		public ResponseEntity<String> AcceptedOrRejectedStatus(@PathVariable int customerid,@PathVariable String status)
		
		{
			LOGGER.info("IF customer has Accepted or Rejected the Sanctioned loan Proposal Updating that status ");
			 
			System.out.println(status);
		esi.AcceptedOrRejectedStatus(customerid, status);
			
			
			
			return new ResponseEntity<String>("Customer has"+" "+ status+" "+"the loan Sanctioned Proposal",HttpStatus.OK);
			
			//"Customer has  "+" "+ status+" "+"the loan Sanctioned Proposal"
			
		}
		
		@GetMapping("/getsingledata/{customerid}")
		public ResponseEntity<LoanApplication> getSingleData(@PathVariable int customerid)
		
		{
			
			
			List<LoanApplication>loans = getAllLoanSubmitted();
			
			
			 for (LoanApplication loan : loans) {
			        if (customerid == loan.getCustomerid()) {
			            // If customerid matches, return the loan data wrapped in ResponseEntity with HTTP status OK
			            return ResponseEntity.ok(loan);
			        }
			    }
			    
			    // If the loop completes without finding a match, throw a custom exception or return an error response
			    throw new CustomerNotFoundException("Customer with ID " + customerid + " not found");
			}
			
		
		
		
			
}		
			
			
		
		
		
		
		
		
		
		
		
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


