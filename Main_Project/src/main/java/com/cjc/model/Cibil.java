package com.cjc.model;

import jakarta.persistence.Entity;
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
