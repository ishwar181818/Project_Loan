package com.acc.servicei;

import java.util.List;

import com.acc.model.LoanApplication;

public interface ServiceI {

	public LoanApplication savedisbursedata(int customerid);

	public String saveLedger(int customerid);

	public String updatepaymntstatus(int customerid, int ledgerid);

	public String updatepreviouspaymntstatus(int customerid, int ledgerid);

	public LoanApplication getacceptdata(int customerid, String status);

	public List<LoanApplication> getAllCustomerSanctionedData();

	public List<LoanApplication> getAcceptedData(String status);
	
	

}
