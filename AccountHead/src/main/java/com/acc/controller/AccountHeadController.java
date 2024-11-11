package com.acc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.acc.model.LoanApplication;
import com.acc.servicei.ServiceI;



@RestController
@RequestMapping("/dis")
public class AccountHeadController {
	
	
	@Autowired
	ServiceI ssi;
	@Autowired
	RestTemplate rt;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AccountHeadController.class);
	
	@PutMapping("/add/{customerid}")
	public ResponseEntity<LoanApplication> loandisbursementdata(@PathVariable int customerid)
	
	{
		LOGGER.info("Disbursing Loan Amount To the Customer");
		
		  LoanApplication loan= ssi.savedisbursedata(customerid);
		
		
		
		
		
		return new ResponseEntity<LoanApplication>(loan,HttpStatus.OK);
		
	}
	
	
	@PutMapping("/led/{customerid}")
	public ResponseEntity<String> ledgergeneration(@PathVariable int customerid)
	
	{
		LOGGER.info("Generating Ledger Data for the Customer ");
		
		
		ssi.saveLedger(customerid);
		
		
		
		return new ResponseEntity<String>("ledger created for the customer",HttpStatus.CREATED);
		
	}
	
	@PutMapping("/pay/{customerid}/{ledgerid}")//for Current emistatus
	public ResponseEntity<String> updatepaymentstatus(@PathVariable int customerid, @PathVariable int ledgerid)
	
	{
		
		LOGGER.info("If Customer Pays First Emi Updating It Into the Ledger");
		
		ssi.updatepaymntstatus(customerid,ledgerid);
		
		
		return new ResponseEntity<String>("current emi status chenged to paid",HttpStatus.OK);
	}
	
	
	@PutMapping("/paid/{customerid}/{ledgerid}")///for previous emistatus
	public ResponseEntity<String> updatepreviouspaymentstatus(@PathVariable int customerid, @PathVariable int ledgerid)
	
	{
		LOGGER.info("If Customer had already paid previous Emi the Updating Previous Emi as paid ");
		
		ssi.updatepreviouspaymntstatus(customerid,ledgerid);
		
		//"previous emi status chenged to paid"
		
		return new ResponseEntity<String>("previous emi status chenged to paid",HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<LoanApplication>> getAllData()
	
	{
		
		
		 String operationExecutiveUrl = "http://localhost:8084/oe/getAllCustomer";  // URL of the OperationExecutive Microservice

	        // Make the GET request to the OperationExecutive service
	        ResponseEntity<List> response = rt.exchange(
	                operationExecutiveUrl, HttpMethod.GET, null, List.class);

	        // Check if the response is successful
	        if (response.getStatusCode() == HttpStatus.OK) {
	            return ResponseEntity.ok(response.getBody());
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	
	@GetMapping("/getaccept/{customerid}/{status}")
	public ResponseEntity<LoanApplication> getsingleacceptedData(@PathVariable int customerid,@PathVariable String status)
	{
		
		try {
            // Call the service method to get loan data
            LoanApplication loan = ssi.getacceptdata(customerid , status);

            // Return the loan data wrapped in ResponseEntity with HTTP status OK
            return ResponseEntity.ok(loan);

        } catch (RuntimeException e) {
            // Catch runtime exception (like loan not found, customer not found, etc.)
            // Return an error message with HTTP status BAD_REQUEST or NOT_FOUND as appropriate
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
	
	
	@GetMapping("/getAll")////Getting All Loans Dsbursed and ledger data for All the Customerrs
	  public List<LoanApplication> getAllCustomerData()
	
	{
		
		
	List<LoanApplication>loans=ssi.getAllCustomerSanctionedData();
		
		
		
		return loans;
		
	}
	
	
	
	}



		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	


