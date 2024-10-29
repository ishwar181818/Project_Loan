package com.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class AllPersonalDoc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int docid;
	@Lob  
	@Column(length = 999999999)
	private byte[] addressproof;
	@Lob  
	@Column(length = 999999999)
	private byte[] pancard;
	
	@Lob 
	@Column(length = 999999999)
	private byte[] incometax;
	
	@Lob 
	@Column(length = 999999999)
	private byte[] adharcard;
	
	@Lob 
	@Column(length = 999999999)
	private byte[] photo;
	
	@Lob 
	@Column(length = 999999999)
	private byte[] signature;
	
	@Lob 
	@Column(length = 999999999)
	private byte[] bankcheque;
	
	@Lob 
	@Column(length = 999999999)
	private byte[] salaryslip;
	
	private String docstatus;
	
	
	
	
	

}
