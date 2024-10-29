package com.ope.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//for Cibil & Enquiry Class Status Approved or Rejected
	@PutMapping("/put/{cid}")
	public Enquiry updateEnquiryStatus(@PathVariable int cid)
	
	{
		
		
		Enquiry e=ssi.updateEnquiryStatus(cid);
		
		
		return e;
		
	}
	
	@GetMapping("/get")
	public List<LoanApplication> getAllCustomerManagerLoanData()//lOAN Application Handler Method Taking alldta
	
	{                                                            //from CustomerManager
		
		
		String url="http://localhost:8082/lo/getAll";/////Customer Manager LoanApplication getAll Method
		                                              //Filling the LoanForm
		
               LoanApplication[]arr=rt.getForObject(url, LoanApplication[].class);
               
               List<LoanApplication>l = Arrays.asList(arr);
               
               
		
		
		       return l;
	}
	
	
	@GetMapping("/getAllCustomer")
	 public List<LoanApplication>getAllLoanAppliedData()
	 
	 {
		 
		 
		List<LoanApplication>l=ssi.getAllLoanAppliedData();
		 
		 
		 return l;
		 
	 }
	
	@PutMapping("/put/{customerid}/{docstatus}")
	public LoanApplication LoanAndDocsVerified(@PathVariable int customerid,@PathVariable String docstatus) {
		
		
		
	LoanApplication loan=	ssi.LoanAndDocsVerified(customerid,docstatus);
		
		
		
	
		return loan;
	
	
	}
	

}
