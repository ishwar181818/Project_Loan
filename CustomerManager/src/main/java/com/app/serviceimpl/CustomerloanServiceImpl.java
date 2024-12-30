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
        
        System.out.println("Output");
	    
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

	@Override
	public void updateCustomerData(int customerid, String json, MultipartFile addressproof, MultipartFile pancard,
	                                MultipartFile incometax, MultipartFile adharcard, MultipartFile photo,
	                                MultipartFile signature, MultipartFile bankCheque, MultipartFile salaryslip, 
	                                List<Enquiry> l) {
	    ObjectMapper om = new ObjectMapper();
	    LoanApplication emp = null;
	    
	    try {
	        // Deserialize the updated data from the JSON
	        emp = om.readValue(json, LoanApplication.class);
	        boolean mobileMatchFound = false;

	        for (Enquiry enq : l) {
	            if (enq.getMobileno() == emp.getCustomermobileno()) {
	                mobileMatchFound = true;
	                emp.setCustomerid(enq.getCid());
	                emp.setCustomername(enq.getFirstname().concat(" ").concat(enq.getLastname()));
	                break;
	            }
	        }

	        if (!mobileMatchFound) {
	            throw new MobileNoNotMatching("MobileNo not matching. Please insert mobileno updated during Enquiry.");
	        }

	        // Fetch the existing loan application from the database using the customerId
	        Optional<LoanApplication> existingLoanApplicationOpt = er.findById(customerid);
	        if (existingLoanApplicationOpt.isPresent()) {
	            LoanApplication existingLoanApplication = existingLoanApplicationOpt.get();
	            
	            // Update fields of the existing LoanApplication
	            existingLoanApplication.setCustomerid(emp.getCustomerid());
	            existingLoanApplication.setCustomername(emp.getCustomername());
	            existingLoanApplication.setDateofbirth(emp.getDateofbirth());
	            existingLoanApplication.setCustomerage(emp.getCustomerage());
	            existingLoanApplication.setRequiretenure(emp.getRequiretenure());
	            existingLoanApplication.setCustomergender(emp.getCustomergender());
	            existingLoanApplication.setCustomeremail(emp.getCustomeremail());
	            existingLoanApplication.setCustomermobileno(emp.getCustomermobileno());
	            existingLoanApplication.setCustomeradditionalmobileno(emp.getCustomeradditionalmobileno());
	            existingLoanApplication.setAmountpaidforhome(emp.getAmountpaidforhome());
	            existingLoanApplication.setTotalloanrequired(emp.getTotalloanrequired());
	            existingLoanApplication.setLoanstatus(emp.getLoanstatus());

	            // Handling personal document status
	            existingLoanApplication.getAllpersonaldoc().setDocstatus(emp.getAllpersonaldoc().getDocstatus());

	            // FDO (Family Details)
	            existingLoanApplication.getFdo().setNooffamilymember(emp.getFdo().getNooffamilymember());
	            existingLoanApplication.getFdo().setNoofchild(emp.getFdo().getNoofchild());
	            existingLoanApplication.getFdo().setMartialstatus(emp.getFdo().getMartialstatus());
	            existingLoanApplication.getFdo().setDependentMember(emp.getFdo().getDependentMember());
	            existingLoanApplication.getFdo().setFamilyincome(emp.getFdo().getFamilyincome());
//emp.getCustomeraddress().getPermanentaddress().getAreaname()
	            // Customer address (Permanent and Local)
	            existingLoanApplication.getCustomeraddress().getPermanentaddress().setAreaname(emp.getCustomeraddress().getPermanentaddress().getAreaname());
	            existingLoanApplication.getCustomeraddress().getPermanentaddress().setCityname(emp.getCustomeraddress().getPermanentaddress().getCityname());
	            existingLoanApplication.getCustomeraddress().getPermanentaddress().setDistrict(emp.getCustomeraddress().getPermanentaddress().getDistrict());
	            existingLoanApplication.getCustomeraddress().getPermanentaddress().setState(emp.getCustomeraddress().getPermanentaddress().getState());
	            existingLoanApplication.getCustomeraddress().getPermanentaddress().setPincode(emp.getCustomeraddress().getPermanentaddress().getPincode());
	            existingLoanApplication.getCustomeraddress().getPermanentaddress().setHouseno(emp.getCustomeraddress().getPermanentaddress().getHouseno());
	            existingLoanApplication.getCustomeraddress().getPermanentaddress().setStreetname(emp.getCustomeraddress().getPermanentaddress().getStreetname());

	            existingLoanApplication.getCustomeraddress().getLocaladdress().setAreaname(emp.getCustomeraddress().getLocaladdress().getAreaname());
	            existingLoanApplication.getCustomeraddress().getLocaladdress().setCityname(emp.getCustomeraddress().getLocaladdress().getCityname());
	            existingLoanApplication.getCustomeraddress().getLocaladdress().setDistrict(emp.getCustomeraddress().getLocaladdress().getDistrict());
	            existingLoanApplication.getCustomeraddress().getLocaladdress().setState(emp.getCustomeraddress().getLocaladdress().getState());
	            existingLoanApplication.getCustomeraddress().getLocaladdress().setPincode(emp.getCustomeraddress().getLocaladdress().getPincode());
	            existingLoanApplication.getCustomeraddress().getLocaladdress().setHouseno(emp.getCustomeraddress().getLocaladdress().getHouseno());
	            existingLoanApplication.getCustomeraddress().getLocaladdress().setStreetname(emp.getCustomeraddress().getLocaladdress().getStreetname());

	            // Account Details
	            existingLoanApplication.getAc().setAccounttype(emp.getAc().getAccounttype());
	            existingLoanApplication.getAc().setAccountbalance(emp.getAc().getAccountbalance());
	            existingLoanApplication.getAc().setAccountholdername(emp.getAc().getAccountholdername());
	            existingLoanApplication.getAc().setAccountstatus(emp.getAc().getAccountstatus());
	            existingLoanApplication.getAc().setAccountnumber(emp.getAc().getAccountnumber());

	            // Guarantor Details
	            existingLoanApplication.getGd().setGuarantorname(emp.getGd().getGuarantorname());
	            existingLoanApplication.getGd().setDateofbirth(emp.getGd().getDateofbirth());
	            existingLoanApplication.getGd().setRelationshipwithcustomer(emp.getGd().getRelationshipwithcustomer());
	            existingLoanApplication.getGd().setMobileno(emp.getGd().getMobileno());
	            existingLoanApplication.getGd().setGurantoradharcardno(emp.getGd().getGurantoradharcardno());
	            existingLoanApplication.getGd().setMortgagedetails(emp.getGd().getMortgagedetails());
	            existingLoanApplication.getGd().setJobdetails(emp.getGd().getJobdetails());
	            existingLoanApplication.getGd().setLocaladdress(emp.getGd().getLocaladdress());
	            existingLoanApplication.getGd().setPermanantaddress(emp.getGd().getPermanantaddress());

	            // Customer Verification Status
	            existingLoanApplication.getCv().setCustomerverificationstatus(emp.getCv().getCustomerverificationstatus());
	            existingLoanApplication.getCv().setVerificationdate(emp.getCv().getVerificationdate());
	            existingLoanApplication.getCv().setRemarks(emp.getCv().getRemarks());

	            
	            // Add other fields to be updated similarly
	            
	            // Handle the personal documents
	            try {
	                existingLoanApplication.getAllpersonaldoc().setAddressproof(addressproof.getBytes());
	                existingLoanApplication.getAllpersonaldoc().setPancard(pancard.getBytes());
	                existingLoanApplication.getAllpersonaldoc().setIncometax(incometax.getBytes());
	                existingLoanApplication.getAllpersonaldoc().setAdharcard(adharcard.getBytes());
	                existingLoanApplication.getAllpersonaldoc().setPhoto(photo.getBytes());
	                existingLoanApplication.getAllpersonaldoc().setSignature(signature.getBytes());
	                existingLoanApplication.getAllpersonaldoc().setBankcheque(bankCheque.getBytes());
	                existingLoanApplication.getAllpersonaldoc().setSalaryslip(salaryslip.getBytes());
	            } catch (IOException e) {
	                e.printStackTrace();
	            }

	            // Save the updated loan application back to the database
	            er.save(existingLoanApplication);
	        } else {
	            throw new LoanApplicationdataNotPresentException("Loan Application for Customer ID " + customerid + " not found.");
	        }

	    } catch (JsonProcessingException e) {
	        e.printStackTrace();
	    }
	}

	
	
	
	

}
