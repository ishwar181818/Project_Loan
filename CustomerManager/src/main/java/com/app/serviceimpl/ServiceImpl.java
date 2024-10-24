package com.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.app.model.Enquiry;
import com.app.repo.Repo;
import com.app.servicei.ServiceI;
@Service
public class ServiceImpl implements ServiceI {
	
	
   @Autowired
	Repo rr;
   @Autowired
  JavaMailSender jms;
	
   
   @Value("${spring.mail.username}")
	private static String FROM_MAIL;
	@Override
	public Enquiry updatecibildata(Enquiry enq) {
		
		
		
		Enquiry eq=rr.save(enq);
		StringBuilder emailContent = new StringBuilder();
		emailContent.append("After the enquiry, we checked your CIBIL score, and below is the status of your loan confirmation:\n\n");
        emailContent.append("Customer ID: ").append(enq.getCid()).append("\n")
                    .append("First Name: ").append(enq.getFirstname()).append("\n")
                    .append("Last Name: ").append(enq.getLastname()).append("\n")
                    .append("Age: ").append(enq.getAge()).append("\n")
                    .append("Email: ").append(enq.getEmail()).append("\n")
                    .append("Mobile No: ").append(enq.getMobileno()).append("\n")
                    .append("Pancard: ").append(enq.getPancard()).append("\n")
                    .append("CIBIL ID: ").append(enq.getCb().getCibilid()).append("\n")
                    .append("CIBIL Score: ").append(enq.getCb().getCibilscore()).append("\n")
                    .append("CIBIL Score Date and Time: ").append(enq.getCb().getCibilscoredateandtime()).append("\n")
                    .append("Status: ").append(enq.getCb().getStatus()).append("\n")
                    .append("CIBIL Remark: ").append(enq.getCb().getCibilremark()).append("\n");
		
		
		
     // Send the email
        SimpleMailMessage simple = new SimpleMailMessage();
        simple.setTo("Sagarhingane7@gmail.com"); // to send
        simple.setTo("divyadahat06@gmail.com");
        simple.setTo("chaudharimanish0@gmail.com");
        
        simple.setTo("rutuagade15@gmail.com");
        simple.setTo("pratikMane07@gmail.com");
        
        
     
        
        
        simple.setFrom(FROM_MAIL);  // send from
        simple.setSubject("Regarding Loan Conformation");
        simple.setText(emailContent.toString());
        
        jms.send(simple);
		
		
		
		
		return eq;
	}
	
	
	

	

	
	
	
	
	
	

}
