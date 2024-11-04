package com.ope.servicei;

import java.util.List;

import com.ope.model.Enquiry;
import com.ope.model.LoanApplication;

public interface ServiceI {
	
	public Enquiry updateEnquiryStatus(int cid);

	public List<LoanApplication> getAllLoanAppliedData();

	

	public LoanApplication LoanAndDocsVerified(int customerid, String docstatus);

	public Enquiry getSingledata(int cid);

	public List<Enquiry> getAllEnquiry();

	public void deleteSingleData(int cid);

	public void deleteCustomerLoanData(int customerid);

	public LoanApplication getSingleLoanData(int customerid);

}
