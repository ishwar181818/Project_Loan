package com.acc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acc.servicei.ServiceI;


@RestController
@RequestMapping("/dis")
public class AccountHeadController {
	
	
	@Autowired
	ServiceI ssi;
	
	
	
	@PutMapping("/add/{customerid}")
	public String loandisbursementdata(@PathVariable int customerid)
	
	{
		
		
		   ssi.savedisbursedata(customerid);
		
		
		
		
		
		return "loan has been disbursed for "+ " :"+customerid;
		
	}
	
	
	@PutMapping("/led/{customerid}")
	public String ledgergeneration(@PathVariable int customerid)
	
	{
		
		
		
		ssi.saveLedger(customerid);
		
		
		
		return "ledger created for the customer";
		
	}
	
	@PutMapping("/pay/{customerid}/{ledgerid}")
	public String updatepaymentstatus(@PathVariable int customerid, @PathVariable int ledgerid)
	
	{
		
		ssi.updatepaymntstatus(customerid,ledgerid);
		
		
		return "current emi status chenged to paid";
	}
	
	
	@PutMapping("/paid/{customerid}/{ledgerid}")///for previous emistatus
	public String updatepreviouspaymentstatus(@PathVariable int customerid, @PathVariable int ledgerid)
	
	{
		
		ssi.updatepreviouspaymntstatus(customerid,ledgerid);
		
		
		return "previous emi status chenged to paid";
	}
	
	
	
	
	
	
	
	
	

}
