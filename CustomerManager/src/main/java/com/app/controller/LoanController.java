package com.app.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class LoanController {
	
	@Autowired
	CustomerloanInterface esi;
	
	@Autowired
	RestTemplate rt;
	
	private String enquirystatus="Approved";
	
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
		
		String url = "http://localhost:8082/cm/get/"+ enquirystatus;
		
		Enquiry[]enq=rt.getForObject(url, Enquiry[].class);
		
		List<Enquiry>l = Arrays.asList(enq);
		
		System.out.println(l);
		
		esi.saveCustomerData(json,addressproof, pancard,incometax, adharcard,photo,signature,bankCheque,salaryslip,l);
		
//		System.out.println(json);
//		System.out.println(pancard);
//		System.out.println(aadharcard);
		
		return new ResponseEntity<String>("Data Saved", HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public List<LoanApplication> getAllLoanSubmittedDetails()
	
	{
		
		List<LoanApplication>list=esi.getAllLoanSubmittedDetails();
		
		
		return list;
	}
	
	

}
