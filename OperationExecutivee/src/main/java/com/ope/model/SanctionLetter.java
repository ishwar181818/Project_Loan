package com.ope.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SanctionLetter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sanctionid;
	
	private String sanctiondate ;
	
	private String applicantname;
	
	private long contactdetails;
	
	private double loanamtsanctioned;
	
	private String interesttype;
	
	private float rateofinterest;
	
	private int loantenureinmonth;
	
	private double monthlyemiamount;
	
	private String modeofpayment;
	
	private String remarks;
	
	private String termscondition;
	
	private String status;

}