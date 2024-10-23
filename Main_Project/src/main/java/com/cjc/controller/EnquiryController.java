package com.cjc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.model.Enquiry;
import com.cjc.servicei.ServiceI;

@RestController
@RequestMapping("/enq")
public class EnquiryController {
	@Autowired
	ServiceI ssi;
	
	
	@PostMapping("/add")
	public Enquiry addEnquiry(@RequestBody Enquiry enq)
	
	{
		
		Enquiry e=ssi.saveData(enq);
		
		
		return e;
		
	}
	
	@GetMapping("/get")
	public Enquiry getSingleData()
	{
		return null;
	}

}
