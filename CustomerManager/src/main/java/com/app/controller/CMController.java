package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
public class CMController {
	
	
	@Autowired
	RestTemplate rt;
	@Autowired
	ServiceI ssi;
	
	@PutMapping("/put/{cid}")
	public  String updateDataOfCibil(@PathVariable int cid, @RequestBody Enquiry enq1)
   
   {
		
		
		String url="http://localhost:8081/enq/getAll";
		
		String url1 ="http://localhost:9091/generate/"+ enq1.getCb().getCibilid();
		
		Enquiry[]arr=rt.getForObject(url, Enquiry[].class);
		
		Cibil cb=rt.getForObject(url1, Cibil.class);
		
		List<Enquiry>l1=Arrays.asList(arr);
		
		
		
		for(Enquiry enq : l1)
			
		{
			
			
			
			if(enq.getCid()==cid)
				
			{
				
				if(enq.getCb() == null)
				{
				   enq.setCb(new Cibil());
				}
				
				
					enq.getCb().setCibilid(enq1.getCb().getCibilid());
					
					enq.getCb().setCibilscore(cb.getCibilscore());
					
					
				
				
				//enq.getCb().setCibilid(enq1.getCb() != null ? enq1.getCb().getCibilid() : 0);
				
				
				
				
				
			if(enq.getCb().getCibilscore()>=801 && enq.getCb().getCibilscore() <= 900 )
				
			{
				
				enq.getCb().setStatus("Excellent");
				
				enq.getCb().setCibilremark("Eligible for Loan");
				
				
				
			}
			
			else if(enq.getCb().getCibilscore()>=761 && enq.getCb().getCibilscore() <= 800)
			{
				
                enq.getCb().setStatus("Very Good");
				
				enq.getCb().setCibilremark("Eligible for Loan");
				
				
			}
				
				
			else if(enq.getCb().getCibilscore()>=701 && enq.getCb().getCibilscore() <= 760)
			{
				
                enq.getCb().setStatus("Good");
				
				enq.getCb().setCibilremark("Eligible for Loan");
				
				
			}
			
			else if(enq.getCb().getCibilscore()>=601 && enq.getCb().getCibilscore() <= 700)
			{
				
                enq.getCb().setStatus("Average");
				
				enq.getCb().setCibilremark("Not Eligible for loan");
				
				
			}
			
			else if(enq.getCb().getCibilscore()>=300 && enq.getCb().getCibilscore() <= 600)
			{
				
                enq.getCb().setStatus("Needs Help");
				
				enq.getCb().setCibilremark("Not Eligible for Loan");
				
				
			}
			
			
			
			Date date = new Date();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String formattedDate = formatter.format(date);
			
			enq.getCb().setCibilscoredateandtime(formattedDate);
			
			
			ssi.updatecibildata(enq);
			
			
        }
			
			
		}
		
		
		
	   
	   
	   
		
		return "CIBIL Score has been patched succesfully for "+":"+ cid ;
	   
	   
   }
	
}
	
	
	
	
	
	
	
	
	


