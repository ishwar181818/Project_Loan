package com.app.servicei;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.model.Enquiry;
import com.app.model.LoanApplication;

public interface CustomerloanInterface {
	
	
	public void saveCustomerData(String json, MultipartFile addressproof, MultipartFile pancard,
			
			MultipartFile incometax ,MultipartFile adharcard,MultipartFile photo,MultipartFile signature,MultipartFile bankCheque,
			
			MultipartFile salaryslip, List<Enquiry> l);

	public List<LoanApplication> getAllLoanSubmittedDetails();

	public void deleteCustomerLoanData(int customerid);

	public LoanApplication getSingleLoanData(int customerid);

	public void deleteAllData();

}
