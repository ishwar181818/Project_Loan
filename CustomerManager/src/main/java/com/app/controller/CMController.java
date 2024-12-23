 package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.client.RestTemplate;

import com.app.model.Cibil;
import com.app.model.Enquiry;
import com.app.servicei.ServiceI;


@RestController
@RequestMapping("/cm")
@CrossOrigin(origins = "http://localhost:3000")
public class CMController {
	
	@Value("${server.port}")
	String port;
	
	//
	
	
	
	@Autowired
	RestTemplate rt;
	@Autowired
	ServiceI ssi;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CMController.class);
	
	@PutMapping("/put/{cid}")
	public  ResponseEntity<String> updateDataOfCibil(@PathVariable int cid)
   
   {
		LOGGER.info("Fetching data from RM And Third Party Api");
		
		String url="http://localhost:8081/enq/getAll";
		
		String url1 ="http://localhost:9091/generate";
		
		Enquiry[]arr=rt.getForObject(url, Enquiry[].class);
		
		Cibil cb=rt.getForObject(url1, Cibil.class);
		
		List<Enquiry>l1=Arrays.asList(arr);
		
		
		
		for(Enquiry enq : l1)
			
		{
			
			
			
			if(enq.getCid()==cid)
				
			{
				Cibil c1 = new Cibil();
				
				
				c1.setCibilscore(cb.getCibilscore());
				
				
				
				//enq.getCb().setCibilid(enq1.getCb() != null ? enq1.getCb().getCibilid() : 0);
				
				
				
				
				
			if(c1.getCibilscore()>=801 && c1.getCibilscore() <= 900 )
				
			{
				
				c1.setStatus("Excellent");
				
				c1.setCibilremark("Eligible for Loan");
				
				
				
			}
			
			else if(c1.getCibilscore()>=761 && c1.getCibilscore() <= 800)
			{
				
				c1.setStatus("Very Good");
				
				c1.setCibilremark("Eligible for Loan");
				
				
			}
				
				
			else if(c1.getCibilscore()>=701 && c1.getCibilscore() <= 760)
			{
				
				c1.setStatus("Good");
				
				c1.setCibilremark("Eligible for Loan");
				
				
			}
			
			else if(c1.getCibilscore()>=601 && c1.getCibilscore() <= 700)
			{
				
				c1.setStatus("Average");
				
				c1.setCibilremark("Not Eligible for loan");
				
				
			}
			
			else if(c1.getCibilscore()>=300 && c1.getCibilscore() <= 600)
			{
				
				c1.setStatus("Needs Help");
				
				c1.setCibilremark("Not Eligible for Loan");
				
				
			}
			
			
			
			Date date = new Date();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String formattedDate = formatter.format(date);
			
			c1.setCibilscoredateandtime(formattedDate);
			
			if(enq.getCb() == null)
				{
				   enq.setCb(c1);
			}
			
			
			ssi.updatecibildata(enq);
			
			
        }
			
			
		}
		
		
		
	   
	   
	   
		
		return new ResponseEntity<String>("CIBIL Score has been patched succesfully for "+":"+"Customer id"+":"+ cid,HttpStatus.OK) ;
	   
	   
   }
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Enquiry>>getAllEnquires()
	
	{
		LOGGER.info("Getting All data");
		
		List<Enquiry>l=ssi.getAllEnquires();
		System.out.println("port Executing is"+":"+port);
		
		return new ResponseEntity<List<Enquiry>>(l,HttpStatus.OK);
		
	}
	
	@GetMapping("/get/{enquirystatus}")//(use for Approved and Rejected)
	public ResponseEntity<List<Enquiry>>showEnquiry(@PathVariable String enquirystatus )
	
	{
		LOGGER.info("Getting single data ");
		
		List<Enquiry>l=ssi.findByenquirystatus(enquirystatus);
		
		
		return new ResponseEntity<List<Enquiry>>(l,HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/del/{cid}")
	public ResponseEntity<String> deleteData(@PathVariable int cid)

	{
		LOGGER.info("Deleting single data");
		
		  ssi.deletedata(cid);

		
		return new ResponseEntity<String>("data has been deleted for"+":"+ cid,HttpStatus.NO_CONTENT);
		
	}
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	


