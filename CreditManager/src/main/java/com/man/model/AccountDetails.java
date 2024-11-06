 package com.man.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AccountDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountid;
	
	private String accounttype;
	
	private double accountbalance;
	
	private String accountholdername;
	
	private String accountstatus;
	
	private long accountnumber;

}
