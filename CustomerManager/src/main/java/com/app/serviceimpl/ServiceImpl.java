package com.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.app.model.Cibil;
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
        emailContent.append("Customer ID: ").append(eq.getCid()).append("\n")
                    .append("First Name: ").append(eq.getFirstname()).append("\n")
                    .append("Last Name: ").append(eq.getLastname()).append("\n")
                    .append("Age: ").append(eq.getAge()).append("\n")
                    .append("Email: ").append(eq.getEmail()).append("\n")
                    .append("Mobile No: ").append(eq.getMobileno()).append("\n")
                    .append("Pancard: ").append(eq.getPancard()).append("\n")
                    .append("CIBIL ID: ").append(eq.getCb().getCibilid()).append("\n")
                    .append("CIBIL Score: ").append(eq.getCb().getCibilscore()).append("\n")
                    .append("CIBIL Score Date and Time: ").append(eq.getCb().getCibilscoredateandtime()).append("\n")
                    .append("Status: ").append(eq.getCb().getStatus()).append("\n")
                    .append("CIBIL Remark: ").append(eq.getCb().getCibilremark()).append("\n");
		
		
		
     // Send the email
        SimpleMailMessage simple = new SimpleMailMessage();
        simple.setTo("ishwarharbade55@gmail.com"); // to send
       
        simple.setFrom(FROM_MAIL);  // send from
        simple.setSubject("Regarding Loan Conformation");
        simple.setText(emailContent.toString());
        
        jms.send(simple);
		
		
		
		
		return eq;
	}
	@Override
	public List<Enquiry> getAllEnquires() {
		
		List<Enquiry>l=rr.findAll();
		
		
		return l;
	}
	@Override
	public List<Enquiry> findByenquirystatus(String enquirystatus) {
		
		List<Enquiry>l=rr.findByEnquirystatus(enquirystatus);
		
		return l;
	}
	@Override
	public void deletedata(int cid) {
		
		
		
		rr.deleteById(cid);
		
		
		
	}
	
	
	

	

	
	
	
	
	
	

}
