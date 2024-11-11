package com.man.servicei;

import java.util.List;


import com.man.model.Creditlimit;
import com.man.model.LoanApplication;
import com.man.model.SanctionLetter;



public interface ServiceI {

	public List<LoanApplication> getAllData();

	

	public void generatecreditLimit(Creditlimit cl,int cid);



	public Creditlimit generateSactionletter(int creditid, SanctionLetter sanctionLetter);



	public LoanApplication getCustomerData(Integer customerId);



	public void AcceptedOrRejectedStatus(int customerid, String status);



	public String setloanstatus(int customerid, String loanstatus);



	public List<LoanApplication> getAllCustomerSanctionedData();



	

	
}
