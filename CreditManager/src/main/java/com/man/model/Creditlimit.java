package com.man.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Data
@Entity
public class Creditlimit {
	
	
	@Id
	private int creditid;
	
	private double creditlimit;
	
	private int requiredtenure;
	
	private int interestrate;
	
	private double monthlyemi;
	
	@OneToOne(cascade=CascadeType.ALL)
	private SanctionLetter  sanctionletter;

}
