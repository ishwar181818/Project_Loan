package com.app.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.exception.LoanApplicationdataNotPresentException;
import com.app.exception.MobileNoNotMatching;
import com.app.model.AccountDetails;
import com.app.model.CustomerAddress;
import com.app.model.AllPersonalDoc;
import com.app.model.CustomerVerfication;
import com.app.model.Enquiry;
import com.app.model.FamilyDependentInfo;
import com.app.model.GuarantorDetails;
import com.app.model.Ledger;
import com.app.model.LoanApplication;
import com.app.model.LoanDisbursement;
import com.app.model.LocalAddress;
import com.app.model.PermanentAddess;
import com.app.model.SanctionLetter;
import com.app.repo.RepoLoanApplication;
import com.app.servicei.CustomerloanInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class CustomerloanServiceImpl implements CustomerloanInterface{
	@Autowired
	RepoLoanApplication er;

	@Override
	public void saveCustomerData(String json, MultipartFile addressproof, MultipartFile pancard,
			
			MultipartFile incometax ,MultipartFile adharcard,MultipartFile photo,MultipartFile signature,MultipartFile bankCheque,
			
			MultipartFile salaryslip,List<Enquiry> l) {
		
		
        ObjectMapper om = new ObjectMapper();
	    
	    LoanApplication emp = null;
	      
	   
	    try {
			emp = om.readValue(json,LoanApplication.class );
			boolean mobileMatchFound = false;
			
			for(Enquiry enq: l)
				
			{
				
				//Customer MobileNo During Enquiry and while Applying for Loan should be same 
				if(enq.getMobileno()==emp.getCustomermobileno())
				{	
					mobileMatchFound = true;
					  
					emp.setCustomerid(enq.getCid());
				
				    emp.setCustomername(enq.getFirstname().concat(" ").concat(enq.getLastname()));
				    
				    break;
				}
				
				
				
				
			}
			
			if (!mobileMatchFound) {
                throw new MobileNoNotMatching("MobileNo not matching. Please insert mobileno updated during Enquiry.");
            }
			
					
					if (emp.getAllpersonaldoc() == null) {
			            emp.setAllpersonaldoc(new AllPersonalDoc());
			        }
					
					if (emp.getFdo() == null) {
			            emp.setFdo(new FamilyDependentInfo());
			        }
					
					if (emp.getCustomeraddress() == null) {
			            emp.setCustomeraddress(new CustomerAddress());
			        }
					
					if (emp.getCustomeraddress().getPermanentaddress() == null) {
			            emp.getCustomeraddress().setPermanentaddress(new PermanentAddess());
			        }
					
					if (emp.getCustomeraddress().getLocaladdress() == null) {
			            emp.getCustomeraddress().setLocaladdress(new LocalAddress());
			        }
					
					if (emp.getAc() == null) {
			            emp.setAc(new AccountDetails());
			            
			            
			        }
					
					if (emp.getGd() == null) {
			            emp.setGd(new GuarantorDetails());
			            
			            
			        }
					
					if (emp.getLd() == null) {
			            emp.setLd(new LoanDisbursement());
			            
			            
			        }
					
					if (emp.getLed() == null) {
			            emp.setLed(new Ledger());
			            
			            
			        }
					
					if (emp.getSanctionletter() == null) {
			            emp.setSanctionletter(new SanctionLetter());
			            
			            
			        }
					
					if (emp.getCv() == null) {
			            emp.setCv(new CustomerVerfication());
			            
			            
			        }
					
					
	    }
					
					
					
					
					
					
		 catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	   if(emp != null)
	   {
		   try {
			//	emp.get(aadharcard.getBytes());
			   
			   emp.getAllpersonaldoc().setAddressproof(addressproof.getBytes());
			   
			   emp.getAllpersonaldoc().setPancard(pancard.getBytes());
			   
			   emp.getAllpersonaldoc().setIncometax(incometax.getBytes());
			   
			   emp.getAllpersonaldoc().setAdharcard(adharcard.getBytes());
			   emp.getAllpersonaldoc().setPhoto(photo.getBytes());
			   
			   emp.getAllpersonaldoc().setSignature(signature.getBytes());
			   
			   emp.getAllpersonaldoc().setBankcheque(bankCheque.getBytes());
			   
			   emp.getAllpersonaldoc().setSalaryslip(salaryslip.getBytes());
				//emp.setPancard(pancard.getBytes());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	   }
	   
	   er.save(emp);
		
		
		
	}

	@Override
	public List<LoanApplication> getAllLoanSubmittedDetails() {
		
		
		List<LoanApplication>List=er.findAll();
		
		return List;
	}

	@Override
	public void deleteCustomerLoanData(int customerid) {
		
		er.deleteById(customerid);
		
		
		
		
	}

	@Override
	public LoanApplication getSingleLoanData(int customerid) {
		
		
		Optional<LoanApplication>op=er.findById(customerid);
		
		
		if(op.isPresent())
			
		{
			LoanApplication loan=op.get();
			
			
			return loan;
			
		}
		
		else
			
		{
			
			
			
			throw new LoanApplicationdataNotPresentException("Loan Application data for "+":"+customerid+"Not Present");
			
		}
		
		
		
	}

	@Override
	public void deleteAllData() {
		
		
		er.deleteAll();
	}
	
	

}
