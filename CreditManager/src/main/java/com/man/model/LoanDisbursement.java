package com.man.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LoanDisbursement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int agreementid;
	
	private int loanno;
	
	private String agreementdate;
	
	private String amountpaytype;
	
	private double totalamount;
	
	private String bankname;
	
	private long accountnumber;
	
	private String ifsccode;
	
	private String accounttype;
	
	private double transferamount;
	
	private String paymentstatus;
	
	private String amountpaiddate;

}
