package com.app.model;

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
	private int cid;
	
	private String firstname;
	
	private String lastname;
	
	private int age;
	
	private String email;
	
	private long mobileno;
	
	private String enquirystatus;
	
	private String username;  

    private String password;
	
	private String pancard;
	
	private String enquiryOpenOrClose;
	@OneToOne(cascade = CascadeType.ALL)
	private Cibil cb;
	
	
	

}
