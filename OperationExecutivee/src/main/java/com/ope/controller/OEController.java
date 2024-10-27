package com.ope.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ope.model.Enquiry;
import com.ope.servicei.ServiceI;

@RestController
@RequestMapping("/oe")
public class OEController {
	@Autowired
	ServiceI ssi;
	
	
	@PutMapping("/put/{cid}")
	public Enquiry updateEnquiryStatus(@PathVariable int cid)
	
	{
		
		
		Enquiry e=ssi.updateEnquiryStatus(cid);
		
		
		return e;
		
	}

}
