package com.ope.model;

import java.util.HashSet;
import java.util.Set;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Entity
@Data
public class LoanApplication {
	@Id
	private int customerid;
	
	private String customername;
	
	private String dateofbirth;
	
	private int customerage;
	
	private int requiretenure;
	
	private String customergender;
	
	private String customeremail;
	
	private long customermobileno;
	
	private long customeradditionalmobileno;
	
	private double amountpaidforhome;
	
	private double totalloanrequired;
	
	private String loanstatus;
	
	@OneToOne(cascade=CascadeType.ALL)
	private AllPersonalDoc allpersonaldoc;
	@OneToOne(cascade=CascadeType.ALL)
	private FamilyDependentInfo fdo;
	@OneToOne(cascade=CascadeType.ALL)
	private CustomerAddress customeraddress;
	@OneToOne(cascade=CascadeType.ALL)
	private AccountDetails ac;
	@OneToOne(cascade=CascadeType.ALL)
	private GuarantorDetails gd;
	@OneToOne(cascade=CascadeType.ALL)
	private LoanDisbursement ld;
	@OneToMany(cascade=CascadeType.ALL)
	private Set<Ledger>led = new HashSet<Ledger>();
	@OneToOne(cascade=CascadeType.ALL)
	private SanctionLetter sanctionletter;
	@OneToOne(cascade=CascadeType.ALL)
	private CustomerVerfication cv;
	
	
	
	

}
