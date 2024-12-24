package com.cjc.controller;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cjc.model.Enquiry;
import com.cjc.servicei.ServiceI;

@RestController
@RequestMapping("/enq")
@CrossOrigin(origins = "http://localhost:3000")
public class EnquiryController {
	@Autowired
	ServiceI ssi;
	
	@Value("${server.port}")
		String port;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(EnquiryController.class);
	@PostMapping("/add")
	public ResponseEntity<Enquiry> addEnquiry(@RequestBody Enquiry enq)
	{
		
		LOGGER.info("Data is Getting added");
		
		 Random rand = new Random();
		 int randomNumForUsername = rand.nextInt(9000) + 1000;  // Generate a random 4-digit number for username
	        int randomNumForPassword = rand.nextInt(9000) + 1000;  // Generate a different random 4-digit number for password

	        // Username will be based on the firstname + random number for username
	        enq.setUsername(enq.getFirstname() + randomNumForUsername);

	        // Password will be based on the firstname + random number for password
	        enq.setPassword(enq.getFirstname() +"@"+ randomNumForPassword);
		Enquiry e=ssi.saveData(enq);	
		return new ResponseEntity<Enquiry>(e,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{cid}")
	public ResponseEntity<Enquiry> getSingleData(@PathVariable int cid)
	{
		
		
		
		LOGGER.info("All Data has been Getting for "+cid);
		
	   Enquiry enq=	ssi.getSingleData(cid);
	
	   
	
		return new ResponseEntity<Enquiry>(enq,HttpStatus.OK);
				
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Enquiry>> getallEnquires(){
		
		System.out.println("port Executing is"+":"+port);
		
		LOGGER.info("All Data has been Get ");
		List<Enquiry> list = ssi.getallEnquiries();
		

		
		return new ResponseEntity<List<Enquiry>>(list,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{cid}")
	public ResponseEntity<String> delete(@PathVariable int cid){
		
		LOGGER.info(" Data has been deleting for "+cid);
		ssi.deleteSingleCustomer(cid);
		
		
		return new ResponseEntity<String>("data has been deleted", HttpStatus.OK);
	}
	
	@PutMapping("/edit/{cid}")
	public ResponseEntity<Enquiry> updateData(@RequestBody Enquiry enq ,@PathVariable int cid)
	
	{
		
		LOGGER.info(" Data is getting updated for "+cid);
		
		Enquiry e=ssi.updateData(enq,cid);
		
		
		
		return new ResponseEntity<Enquiry>(e,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delAll")
	public ResponseEntity<String> deleteAllData()
	
	{
		LOGGER.info("  Deleting All Data " );
		ssi.deleteAllData();
		
	return new ResponseEntity<String>("All data has been deleted",HttpStatus.OK);
		
		
		
	}
	
	@GetMapping("/verify/{username}/{password}")
    public ResponseEntity<?> getUserByCredentials(@PathVariable String username, @PathVariable String password) {
        // Call the service method to get user by username and password
        Enquiry enq = ssi.getUserByUsernameAndPassword(username, password);
        
        System.out.println(enq.getUsername()+enq.getPassword());
        
        if (enq != null) {
            return new ResponseEntity<>(enq, HttpStatus.OK);  // User found
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);  // User not found
        }

}
	
}
