package com.acc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class FamilyDependentInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dependentinfoid;
	
	private int nooffamilymember;
	
	private int noofchild;
	
	private String martialstatus;
	
	private String dependentMember;
	
	private double familyincome;
	
	

}
