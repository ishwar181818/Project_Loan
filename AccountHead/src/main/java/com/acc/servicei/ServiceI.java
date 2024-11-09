package com.acc.servicei;

public interface ServiceI {

	public String savedisbursedata(int customerid);

	public String saveLedger(int customerid);

	public String updatepaymntstatus(int customerid, int ledgerid);

	public String updatepreviouspaymntstatus(int customerid, int ledgerid);

	
	
	

}
