package com.acc.servicei;

import com.acc.model.LoanApplication;

public interface ServiceI {

	public LoanApplication savedisbursedata(int customerid);

	public String saveLedger(int customerid);

	public String updatepaymntstatus(int customerid, int ledgerid);

	public String updatepreviouspaymntstatus(int customerid, int ledgerid);

	public LoanApplication getacceptdata(int customerid, String status);

	
	
	

}
