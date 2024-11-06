package com.man.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class LocalAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int localaddressid;
	
    private String areaname;
	
	private String cityname;
	
	private String district;
	
	private String state;
	
	private long pincode;
	
	private int houseno;
	
	private String streetname;

}
