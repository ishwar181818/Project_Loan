package com.ope.servicei;

import java.util.List;

import com.ope.model.Enquiry;
import com.ope.model.LoanApplication;

public interface ServiceI {
	
	public Enquiry updateEnquiryStatus(int cid);

	public List<LoanApplication> getAllLoanAppliedData();

	

	public LoanApplication LoanAndDocsVerified(int customerid, String docstatus);

}
