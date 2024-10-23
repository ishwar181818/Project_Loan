package com.cjc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/getall")
	public List<Enquiry> getallEnquires(){
		List<Enquiry> list = ssi.getallEnquiries();
		return list;
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Enquiry> delete(@PathVariable("id") int id){
		ssi.deleteSingleCustomer(id);
		return new ResponseEntity<Enquiry>(HttpStatus.OK);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Enquiry> updateCustomer(@RequestBody Enquiry enq)
	{
		Enquiry e = ssi.editCustomer(enq);
		return new ResponseEntity<Enquiry> (e,HttpStatus.OK);
	}

}
