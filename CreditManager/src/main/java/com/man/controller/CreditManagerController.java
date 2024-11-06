package com.man.controller;

import java.util.Arrays;
import java.util.List;

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
	
	
	
	@PostMapping("/gen/{cid}")
	public List<Enquiry>generateData(@PathVariable int cid)
	
	{
		
		
		Creditlimit cl = new Creditlimit();
		
		String url="http://localhost:8082/cm/get/"+ enquirystatus;
		
		
		
		Enquiry[]arr=rt.getForObject(url, Enquiry[].class);
		
		List<Enquiry>l = Arrays.asList(arr);
		
		
		for(Enquiry eq: l)
			
		{
			
			if(cid == eq.getCid() && eq.getEnquirystatus().equals(enquirystatus))
				
			{
				
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
			
			
		cl.setInterestrate(2);
		
		
		esi.generatecreditLimit(cl,cid);
		
		return l;
	}
	
	
		@GetMapping("/get")
		public List<LoanApplication> getAllLoanSubmitted()
		
		{
			
			
			List<LoanApplication>l=esi.getAllData();
			
			
			return l;
		}
		
		@PutMapping("/put/{customerid}/{status}")
		public String AcceptedOrRejectedStatus(@PathVariable int customerid,@PathVariable String status)
		
		{
			 
			 
			System.out.println(status);
		esi.AcceptedOrRejectedStatus(customerid, status);
			
			
			
			return "Customer has  "+" "+ status+" "+"the loan Sanctioned Proposal";
			
		}
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


