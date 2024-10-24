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
	public ResponseEntity<Enquiry> addEnquiry(@RequestBody Enquiry enq)
	{
		Enquiry e=ssi.saveData(enq);	
		return new ResponseEntity<Enquiry>(e,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{cid}")
	public ResponseEntity<Enquiry> getSingleData(@PathVariable int cid)
	{
		
		
	Enquiry enq=	ssi.getSingleData(cid);
	
		return new ResponseEntity<Enquiry>(enq,HttpStatus.OK);
				
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Enquiry>> getallEnquires(){
		List<Enquiry> list = ssi.getallEnquiries();
		
		return new ResponseEntity<List<Enquiry>>(list,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{cid}")
	public ResponseEntity<String> delete(@PathVariable int cid){
		ssi.deleteSingleCustomer(cid);
		return new ResponseEntity<String>("data has been deleted", HttpStatus.OK);
	}
	
	@PutMapping("/edit/{cid}")
	public ResponseEntity<Enquiry> updateData(@RequestBody Enquiry enq ,@PathVariable int cid)
	
	{
		
		Enquiry e=ssi.updateData(enq,cid);
		
		
		
		return new ResponseEntity<Enquiry>(e,HttpStatus.OK);
		
	}
	
	
	public ResponseEntity<String> deleteAllData()
	
	{
		
		ssi.deleteAllData();
		
	return new ResponseEntity<String>("All data has been deleted",HttpStatus.OK);
		
		
		
	}

}
