package com.cjc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cjc.count.EnquiryCountDto;
import com.cjc.exception.EnquiryNotavailbale;
import com.cjc.exception.InvalidCredentialsException;
import com.cjc.exception.MailidInvalidException;
import com.cjc.exception.MobileNoInvalidException;
import com.cjc.exception.NameNotAccepatable;
import com.cjc.exception.NotEligibleforLoanException;
import com.cjc.exception.PancardNotAccepable;
import com.cjc.model.Enquiry;
import com.cjc.repo.Repo;
import com.cjc.servicei.ServiceI;
@Service
public class ServiceImpl implements ServiceI {
	
	@Value("${spring.mail.username}")
	private static String FROM_MAIL;
	
	String enquirystatus="pending";
	
	
	
	@Autowired
	Repo rr;
	
	@Autowired
    private JavaMailSender emailSender; 

	@Override
	public Enquiry saveData(Enquiry enq) {
		
		enq.setEnquirystatus(enquirystatus);
		long digit =0;
		
    for(long i = enq.getMobileno() ; i > 0 ;i= i/10)
		
	    {
		
		
			digit++;
			
		}
		
		if(digit== 10)
			
		{
			System.out.println("Mobile No is Valid");
			
		}
		
		else
			
		{
			throw new MobileNoInvalidException("Mobile No is Invalid");
			
			
		}
		
		if(enq.getEmail().contains("@gmail.com"))
			
		{
			
			System.out.println("Email is valid");
		}
		
		else
			
		{
			
			throw new MailidInvalidException("Email is Invalid");
			
		}
		
		if(enq.getAge() >= 18)
		{
			
			
			System.out.println("Person Eligible for Applying For the Loan");
			
			
		}
		
		else {
			
			throw new NotEligibleforLoanException("Not Eligible For Loan as age is less than 18 years ");
			
		}
		
		String panCard = enq.getPancard();
		
		if (panCard.length() != 10) {
			throw new PancardNotAccepable("PAN card must be exactly 10 characters long");
		}
		
		
		
		
		
		
		for (char c : panCard.toCharArray()) {
			if (!((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z'))) {
				throw new PancardNotAccepable("PAN card Not acceptable: must contain only letters(Uppercase) and digits");
			}
		}
       
       System.out.println("Pancard is Valid");
       
    
       for(char name:enq.getFirstname().toCharArray())
    	   
       {
    	   
    	   if(!((name >= 'a' && name <='z') || (name >= 'A' && name <='Z')))
    		   
    	   {
    		   throw new NameNotAccepatable("First Name Not accepatable");
    		   
    	   }
    	   
       }
       
       System.out.println("FirstName is Valid");
       
for(char l:enq.getLastname().toCharArray())
    	   
       {
    	   
    	   if(!((l >= 'a' && l <='z') || (l >= 'A' && l <='Z')))
    		   
    	   {
    		   throw new NameNotAccepatable("last Name Not accepatable");
    		   
    	   }
    	   
       }


      System.out.println("Last Name is Valid");
      
      enq.setEnquiryOpenOrClose("Open");
		Enquiry e = rr.save(enq);
		
		// Create the email content directly here
        String subject = "Your Account Credentials";
        String text = "Hello " + enq.getFirstname() + ",\n\n" +
                "Your account has been created successfully. Here are your credentials:\n" +
                "Username: " + enq.getUsername() + "\n" +
                "Password: " + enq.getPassword() + "\n\n" +
                "Regards,\n" +
                "Your Bank";

        // Create SimpleMailMessage directly inside saveUser method
        SimpleMailMessage message = new SimpleMailMessage();
       message.setFrom(FROM_MAIL);
        message.setTo(enq.getEmail()); // Set recipient email
        message.setSubject(subject);   // Set subject
        message.setText(text);         // Set email body text

        // Send the email
        emailSender.send(message); // Send the email directly
    
		
		
		return e;
	}

	@Override
	public List<Enquiry> getallEnquiries() {
		
		
	List<Enquiry>l	=rr.findAll();
		return l
				;
	}

	@Override
	public void deleteSingleCustomer(int cid) {
		
		
		rr.deleteById(cid);
		
		
		
		
	}

	@Override
	public Enquiry updateData(Enquiry enq, int cid) {
		
	Optional<Enquiry>op=	rr.findById(cid);
	
	if(op.isPresent())
		
	{
		
		Enquiry enq1=op.get();
		
		//enq1.setFirstname(enq.getFirstname());
		
		//enq1.setLastname(enq.getLastname());
		
		//enq1.setAge(enq.getAge());
		
		enq1.setEmail(enq.getEmail());
		
		enq1.setMobileno(enq.getMobileno());
		
		//enq1.setPancard(enq.getPancard());
		
		rr.save(enq1);
		
		return enq1;
		
		
		
	}
	
	else {
		
		
		throw new EnquiryNotavailbale("Enquiry Not available for "+":"+cid);
		
		
	}
	
		
		
		
	}

	@Override
	public Enquiry getSingleData(int cid) {
		
		
		Optional<Enquiry>op=  rr.findById(cid);
		
		if(op.isPresent())
			
		{
			
			Enquiry enq=op.get();
			
			
			return enq;
			
		}
		
		
		else {
			
			
			throw new EnquiryNotavailbale("Enquiry Not available for "+":"+cid);
			
			
		}
		
		
		
		
		
		
	}

	@Override
	public void deleteAllData() {
		
		rr.deleteAll();
		
		
	}

	@Override
	public Enquiry getUserByUsernameAndPassword(String username, String password) {
		
	Enquiry enq	=rr.findByUsernameAndPassword(username, password);
		
	if (enq != null) {
   return enq;
 } else {
      throw new InvalidCredentialsException("Invalid Username and password");
  }
	}

	@Override
	public EnquiryCountDto getEnquiryStatusCounts() {
		// TODO Auto-generated method stub
		return rr.getEnquiryStatusCounts();
	}
	
	
	

	
	
	
	

}
