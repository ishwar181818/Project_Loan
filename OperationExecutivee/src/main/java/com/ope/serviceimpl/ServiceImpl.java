package com.ope.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.ope.exception.AlreadyApprovedORRejectException;
import com.ope.exception.EnquiryNotAvailable;
import com.ope.exception.LoanApplicationdataNotPresentException;
import com.ope.model.Enquiry;
import com.ope.model.LoanApplication;
import com.ope.repo.Repo;
import com.ope.repo.RepoLoanApplication;
import com.ope.servicei.ServiceI;
@Service
public class ServiceImpl implements ServiceI{
	@Autowired
	Repo rr;
	@Autowired
	RepoLoanApplication rla;////For Loan Application
	
	@Autowired
	  JavaMailSender jms;
	
	private String statussubmitted="Verified";
	
	
	
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
				
				
			throw new AlreadyApprovedORRejectException("Customer Enquiry Status Already Approved or Rejected before");
				
				
			}
			
			
		}
		
		else
			
		{
			throw new EnquiryNotAvailable("Enquiry Not available for "+":"+cid);
			
			
		}
		
		
		
		
	}

	@Override
	public List<LoanApplication> getAllLoanAppliedData() {
		
		List<LoanApplication>l=rla.findAll();
		
		
		return l;
	}

	@Override
	public LoanApplication LoanAndDocsVerified(int customerid,String docstatus) {
		
		
		Optional<LoanApplication>op=rla.findById(customerid);
		
		
		if(op.isPresent())
		{
			
			
			LoanApplication loan= op.get();
			
			
			if(loan.getAllpersonaldoc().getDocstatus().equals(statussubmitted)) 
			
			{
				
				
				
				loan.setLoanstatus(docstatus);
				
				loan.getCv().setCustomerverificationstatus(docstatus);
				
				loan.getCv().setRemarks("Loan Sanctioning is in Process");
				
				Date date = new Date();
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				String formattedDate = formatter.format(date);
				
				loan.getCv().setVerificationdate(formattedDate);
			
			LoanApplication loanverified=rla.save(loan);
			
			StringBuilder emailBody = new StringBuilder();
	        emailBody.append("Dear ").append(loan.getCustomername()).append(",\n\n")
	                .append("Your loan application has been submitted with the following details:\n\n")
	                .append("Date of Birth: ").append(loan.getDateofbirth()).append("\n")
	                .append("Age: ").append(loan.getCustomerage()).append("\n")
	                .append("Required Tenure: ").append(loan.getRequiretenure()).append(" years\n")
	                .append("Gender: ").append(loan.getCustomergender()).append("\n")
	                .append("Mobile No: ").append(loan.getCustomermobileno()).append("\n")
	                .append("Total Loan Required: ").append(loan.getTotalloanrequired()).append("\n")
	                .append("Loan Status: ").append(loan.getLoanstatus()).append("\n")
	                .append("Account Holder Name: ").append(loan.getAc().getAccountholdername()).append("\n")
	                .append("Account Type: ").append(loan.getAc().getAccounttype()).append("\n")
	                .append("Guarantor Name: ").append(loan.getGd().getGuarantorname()).append("\n")
	                .append("Verification date: ").append(loan.getCv().getVerificationdate()).append("\n")
	                .append("Customer Verification Status: ").append(loan.getCv().getCustomerverificationstatus()).append("\n")
	                .append("Remarks: ").append(loan.getCv().getRemarks()).append("\n\n")
	                
	                .append("Thank you for your application!\n\n")
	                .append("Best regards,\n")
	                .append("Your Loan Team");

	        SimpleMailMessage simple = new SimpleMailMessage();
	        simple.setTo("ishwarharbade55@gmail.com"); // to send
	       
	        simple.setFrom(FROM_MAIL);  // send from
	        simple.setSubject("Congragulations! Your Loan Approved and In Process of Sanctioning");
	        simple.setText(emailBody.toString());
	        
	        jms.send(simple);
			
			
			return loanverified;
			
			}
			
		
		else {
			
			
		
			throw  new RuntimeException("Loan docs are NotSubmitted or verified yet");
			
			
			
			
		}
		
		
		}
		
		else {
			
			throw new EnquiryNotAvailable("Enquiry Not available for "+":"+customerid);
			
		}
		
		
	}

	@Override
	public Enquiry getSingledata(int cid) {
		
		Optional<Enquiry>op= rr.findById(cid);
		
		
			
			if(op.isPresent())
				
			{
				Enquiry eq = op.get();
				
				return eq;
				
			}
			
			else
				
			{
				
				throw new EnquiryNotAvailable("Enquiry Not available for "+":"+cid);
				
				
			}
		
		
		
		
	}

	@Override
	public List<Enquiry> getAllEnquiry() {
		
		
		List<Enquiry>l=rr.findAll();
		
		
		return l;
	}

	@Override
	public void deleteSingleData(int cid) {
		
		
		rr.deleteById(cid);
		
		
		
	}

	@Override
	public void deleteCustomerLoanData(int customerid) {
		
		
		
		rla.deleteById(customerid);
		
		
	}

	@Override
	public LoanApplication getSingleLoanData(int customerid) {
		
		Optional<LoanApplication>op=rla.findById(customerid);
		
		if(op.isPresent())
			
		{
			
		LoanApplication loan	= op.get();
			
			return loan;
		}
		
		else
			
		{
			
			
			throw new LoanApplicationdataNotPresentException("Loan Application data for "+":"+customerid+"Not Present");
			
		}
		
		
		
	}
	
	

}
