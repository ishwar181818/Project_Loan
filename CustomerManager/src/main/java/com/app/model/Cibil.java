package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cibil {
	
	@Id
	private int cibilid;
	
	private int cibilscore;
	
	private String cibilscoredateandtime;
	
	private String status;
	
	private String cibilremark;
	
	
	

}
