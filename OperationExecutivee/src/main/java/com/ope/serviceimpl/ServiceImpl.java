package com.ope.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ope.model.Enquiry;
import com.ope.repo.Repo;
import com.ope.servicei.ServiceI;
@Service
public class ServiceImpl implements ServiceI{
	@Autowired
	Repo rr;
	
	@Autowired
	  JavaMailSender jms;
	
	@Value("${spring.mail.username}")
	private static String FROM_MAIL;

	@Override
	public Enquiry updateEnquiryStatus(int cid) {
		
		
		Optional<Enquiry>op=rr.findById(cid);
		
		if(op.isPresent())
			
		{
			
			Enquiry e=op.get();
			
			if(e.getEnquirystatus().equals("pending"))
				
			{
			
			if(e.getCb().getStatus().equals("Excellent")||e.getCb().getStatus().equals("Very Good")||e.getCb().getStatus().equals("Good"))
				
			{
				e.setEnquirystatus("Approved");
				
				rr.save(e);
				
				StringBuilder emailContent = new StringBuilder();
				emailContent.append("After the enquiry, we checked your CIBIL score, and below is the status of your loan confirmation:\n\n");
		        emailContent.append("Customer ID: ").append(e.getCid()).append("\n")
		                    .append("First Name: ").append(e.getFirstname()).append("\n")
		                    .append("Last Name: ").append(e.getLastname()).append("\n")
		                    .append("Age: ").append(e.getAge()).append("\n")
		                    .append("Email: ").append(e.getEmail()).append("\n")
		                    .append("Mobile No: ").append(e.getMobileno()).append("\n")
		                    .append("Pancard: ").append(e.getPancard()).append("\n")
		                    .append("Enquiry Status: ").append(e.getEnquirystatus()).append("\n")
		                    .append("CIBIL ID: ").append(e.getCb().getCibilid()).append("\n")
		                    .append("CIBIL Score: ").append(e.getCb().getCibilscore()).append("\n")
		                    .append("CIBIL Score Date and Time: ").append(e.getCb().getCibilscoredateandtime()).append("\n")
		                    .append("Status: ").append(e.getCb().getStatus()).append("\n")
		                    .append("CIBIL Remark: ").append(e.getCb().getCibilremark()).append("\n");
				
				
				
		     // Send the email
		        SimpleMailMessage simple = new SimpleMailMessage();
		        simple.setTo("ishwarharbade55@gmail.com"); // to send
		       
		        simple.setFrom(FROM_MAIL);  // send from
		        simple.setSubject("Regarding Loan Status Approved or Not");
		        simple.setText(emailContent.toString());
		        
		        jms.send(simple);
				return e;
				
				
			}
			
			else
			{
				
				e.setEnquirystatus("Rejected");
				rr.save(e);
				
				StringBuilder emailContent = new StringBuilder();
				emailContent.append("After the enquiry, we checked your CIBIL score, and below is the status of your loan confirmation:\n\n");
		        emailContent.append("Customer ID: ").append(e.getCid()).append("\n")
		                    .append("First Name: ").append(e.getFirstname()).append("\n")
		                    .append("Last Name: ").append(e.getLastname()).append("\n")
		                    .append("Age: ").append(e.getAge()).append("\n")
		                    .append("Email: ").append(e.getEmail()).append("\n")
		                    .append("Mobile No: ").append(e.getMobileno()).append("\n")
		                    .append("Pancard: ").append(e.getPancard()).append("\n")
		                    .append("Enquiry Status: ").append(e.getEnquirystatus()).append("\n")
		                    .append("CIBIL ID: ").append(e.getCb().getCibilid()).append("\n")
		                    .append("CIBIL Score: ").append(e.getCb().getCibilscore()).append("\n")
		                    .append("CIBIL Score Date and Time: ").append(e.getCb().getCibilscoredateandtime()).append("\n")
		                    .append("Status: ").append(e.getCb().getStatus()).append("\n")
		                    .append("CIBIL Remark: ").append(e.getCb().getCibilremark()).append("\n");
				
				
				
		     // Send the email
		        SimpleMailMessage simple = new SimpleMailMessage();
		        simple.setTo("ishwarharbade55@gmail.com"); // to send
		       
		        simple.setFrom(FROM_MAIL);  // send from
		        simple.setSubject("Regarding Loan Status Approved or Not");
		        simple.setText(emailContent.toString());
		        
		        jms.send(simple);
				return e;
			}
			}
			
			else {
				
				
			throw new RuntimeException("Customer Enquiry Status Already Approved or Rejected before");
				
				
			}
			
			
		}
		
		else
			
		{
			return null;
			
			
		}
		
		
		
		
	}

}
