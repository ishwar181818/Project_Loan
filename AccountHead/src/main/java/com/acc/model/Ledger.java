package com.acc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Ledger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ledgerid;
	
	private String ledgercreateddate;
	
	private double totalloanamount;
	
	private double payableamountwithinterest;
	
	private int tenure;
	
	private double monthlyemi;
	
	private double amountpaidtilldate;
	
	private double remainingamount;
	
	private String nextemidatestart;
	
	private String nextemidateend;
	
	private int defaultercount;
	
	private String previousemistatus;
	
	private String currentmonthemistatus;
	
	private String loanenddate;
	
	private String loanstatus;
	
	
	

}
