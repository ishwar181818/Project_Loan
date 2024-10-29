package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SanctionLetter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sanctionid;
	
	private float rateofinterest;

}
