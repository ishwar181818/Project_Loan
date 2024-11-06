package com.man.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class GuarantorDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gurantorid;
	
	private String guarantorname;
	
	private String dateofbirth;
	
	private String relationshipwithcustomer;
	
	private long mobileno;
	private long gurantoradharcardno;
	
	private String mortgagedetails;
	
	private String jobdetails;
	
	private String localaddress;
	
	private String permanantaddress;
	
	
	
	

}
