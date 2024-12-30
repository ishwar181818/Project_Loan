package com.cjc.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Enquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;
	
	private String firstname;
	
	private String lastname;
	
	private int age;
	
	private String email;
	
	private long mobileno;
	
	private String pancard;
	
	private String username;  // New field for username

    private String password;  // New field for password
	
	private String enquirystatus;
	
	private String enquiryOpenOrClose;
	@OneToOne(cascade = CascadeType.ALL)
	private Cibil cb;
	
	
	

}
