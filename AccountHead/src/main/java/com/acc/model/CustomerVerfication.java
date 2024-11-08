package com.acc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CustomerVerfication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerverificationid;
	
	private String customerverificationstatus;
	
	private String verificationdate;
	
	
	private String remarks;
	
	
	
	

}
