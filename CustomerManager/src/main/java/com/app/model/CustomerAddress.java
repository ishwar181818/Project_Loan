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
public class CustomerAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customeraddressid;
	@OneToOne(cascade=CascadeType.ALL)
	private PermanentAddess permanentaddress;
	@OneToOne(cascade=CascadeType.ALL)
	private LocalAddress localaddress;
	
	

}
