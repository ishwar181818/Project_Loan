package com.acc.serviceimpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acc.exception.CustomerNotFoundException;
import com.acc.exception.LoanstatusNotSanctionedOrSanctionLetterNotAcceptedException;
import com.acc.exception.SanctionLetterNotExceptedException;
import com.acc.model.Ledger;
import com.acc.model.LoanApplication;
import com.acc.repo.Repo;
import com.acc.servicei.ServiceI;
@Service
public class ServiceImpl implements ServiceI {
	
	private String loanstatus="Disbursed";
	
	@Autowired
	 Repo rr;

	@Override
	public LoanApplication savedisbursedata(int customerid) {
		
		Optional<LoanApplication>op=rr.findById(customerid);
		
		if(op.isPresent())
			
		{
			
		    LoanApplication loan=op.get();
		    
		    
		    if(loan.getSanctionletter().getStatus().equals("Accepted"))
		    {
			
			Random rm = new Random();
			
			int no= rm.nextInt(10000, 99999);
			
			loan.getLd().setLoanno(no);
			
            Date date = new Date();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String formattedDate = formatter.format(date);
			
			loan.getLd().setAgreementdate(formattedDate);
			
			loan.getLd().setAmountpaytype(loan.getSanctionletter().getModeofpayment());
			
			loan.getLd().setTotalamount(loan.getSanctionletter().getLoanamtsanctioned());
			
			List<String> bankNames = Arrays.asList(
		            "Bank of America", "Chase", "Wells Fargo", "Citibank", "HSBC", "PNB Bank", "Barclays"
		        );
			
			Random random = new Random();
	        String bankName = bankNames.get(random.nextInt(bankNames.size()));
	        
	        loan.getLd().setBankname(bankName);
			
		
			loan.getLd().setAccountnumber(loan.getAc().getAccountnumber());
			
			loan.getLd().setIfsccode(bankName+"001769");
			
			
			loan.getLd().setAccounttype(loan.getAc().getAccounttype());
			
			loan.getLd().setTransferamount(loan.getSanctionletter().getLoanamtsanctioned());
			
			loan.getLd().setPaymentstatus("Paid");
			
			loan.getLd().setAmountpaiddate(formattedDate);
			
			loan.setLoanstatus(loanstatus);
			
		LoanApplication loandisbursed=	rr.save(loan);
		
		 return loandisbursed;
		 
			
		    }
		    
		    else
		    	
		    {
		    	
		    	throw new SanctionLetterNotExceptedException("Sanction letter is yet to be accepted by customer or it may be Rejected By customer");
		    	
		    }
			
		    
		   
			
		}
		
		else
			
		{
			
			throw new CustomerNotFoundException("Customer Not Present");
		}
		
		
		
	}

	@Override
	public String saveLedger(int customerid) {
		
		Optional<LoanApplication>op=rr.findById(customerid);
		
		if(op.isPresent())
			
		{
			LoanApplication loan =op.get();
			
			if(loan.getSanctionletter().getStatus().equals("Accepted"))
				
			{
			int b = loan.getSanctionletter().getLoantenureinmonth();
			System.out.println(b);
			double c = loan.getSanctionletter().getMonthlyemiamount();
			System.out.println(c);
			double PayableAmountwithinterest = c * b;
			
			System.out.println(PayableAmountwithinterest);
			
			LocalDate currentDate = LocalDate.now();
			
//			 double totalPayableAmount = loanAmount * (1 + interestRate * tenure);
			
//			double remainingAmount = totalLoanAmount - (monthlyEMI * i);
			
			int months =  loan.getRequiretenure();
			
			for(int i =1 ; i <= months ; i++)
				
			{
				
				Ledger ledger = new Ledger();
				
				ledger.setLedgercreateddate("08-11-2024");
				
				ledger.setTotalloanamount(loan.getSanctionletter().getLoanamtsanctioned());
				
				
				
				ledger.setPayableamountwithinterest(PayableAmountwithinterest);
				
				ledger.setTenure(loan.getSanctionletter().getLoantenureinmonth());
				
				ledger.setMonthlyemi(loan.getSanctionletter().getMonthlyemiamount());
				
				ledger.setAmountpaidtilldate(ledger.getMonthlyemi()*i);
				
				ledger.setRemainingamount(ledger.getPayableamountwithinterest()-(ledger.getMonthlyemi()*i));//check
				
//				LocalDate nextEMIStartDate = currentDate.plusMonths(i - 1);
				
				LocalDate nextEMIStartDate = currentDate.plusMonths(i - 1);
				
				ledger.setNextemidatestart(nextEMIStartDate.toString());
				
				LocalDate nextEMIEndDate = currentDate.plusMonths(i);
				
				ledger.setNextemidateend(nextEMIEndDate.toString());
				
				ledger.setDefaultercount(0);
				
				ledger.setPreviousemistatus("--");//check
				
				ledger.setCurrentmonthemistatus("Not paid");
				
				ledger.setLoanenddate(currentDate.plusMonths(months).toString());
				
				ledger.setLoanstatus("Active");
				
				
				loan.getLed().add(ledger);
				
				rr.save(loan);
				
				
				
			}
			
			return "Ledger Created";
			
			
			}	
			
			else
				
			{
				
				throw new SanctionLetterNotExceptedException("Sanction letter is Rejected By Customer no need for Ledger generation");
				
			}
			
			
		}
		
		else
			
		{
			
			throw new CustomerNotFoundException("Customer Not Present");
			
		}
		
		
		
		
		
		
		
	}

	
	//Below method For Updating Current emi status
	@Override
	public String updatepaymntstatus(int customerid, int ledgerid) {
		
		Optional<LoanApplication>op=rr.findById(customerid);
		
		if (op.isPresent()) {
	        LoanApplication loan = op.get();
	        Set<Ledger> led = loan.getLed();

	        // Collect the ledgers that need to be updated
	        Ledger ledgerToUpdate = null;
	        for (Ledger ld : led) {
	            if (ledgerid == ld.getLedgerid()) {
	                ledgerToUpdate = ld;
	                break;  // No need to continue once we've found the correct ledger
	            }
	        }

	        if (ledgerToUpdate != null) {
	            ledgerToUpdate.setCurrentmonthemistatus("Paid");
	            rr.save(loan);  // Save the loan after modifying the ledger status
	        }
	    }

	    return "Payment status updated successfully.";
		
	
}

	@Override
	public String updatepreviouspaymntstatus(int customerid, int ledgerid) {
		
		Optional<LoanApplication> op = rr.findById(customerid);

//	    if (op.isPresent()) {
//	        LoanApplication loan = op.get();
//	        Set<Ledger> led = loan.getLed();
//	        Ledger previousLedger = null;  // Track the previous ledger's status
//
//	        for (Ledger ld : led) {
//	            if (ledgerid == ld.getLedgerid()) {
//	                // Update previousemistatus based on previous ledger's currentemistatus
//	                if (previousLedger != null) {
//	                    ld.setPreviousemistatus(previousLedger.getCurrentmonthemistatus());
//	                } else {
//	                    // For the first ledger, leave previousemistatus blank
//	                    ld.setPreviousemistatus("");
//	                }
//	                rr.save(loan);  // Save the loan after modifying the ledger status
//	                break;  // Exit the loop once the ledger is found and updated
//	            }
//	            previousLedger = ld;  // Update the previous ledger after each iteration
//	        }
//	    }
//
//	    return "Previous payment status updated successfully.";
//		
//        
//
//	}   
	
	
		
		if (op.isPresent()) {
	        LoanApplication loan = op.get();
	        Set<Ledger> led = loan.getLed();

	        // Collect the ledgers that need to be updated
	        Ledger ledgerToUpdate = null;
	        for (Ledger ld : led) {
	            if (ledgerid == ld.getLedgerid()) {
	                ledgerToUpdate = ld;
	                break;  // No need to continue once we've found the correct ledger
	            }
	        }

	        if (ledgerToUpdate != null) {
	            ledgerToUpdate.setPreviousemistatus("Paid");
	            rr.save(loan);  // Save the loan after modifying the ledger status
	        }
	    }

	    return "Payment status updated successfully.";
	
}

	@Override
	public LoanApplication getacceptdata(int customerid , String status) {
		
		
		Optional <LoanApplication>op=rr.findById(customerid);
		
		if(op.isPresent())
			
		{
			
			
			LoanApplication loan = op.get();
			
	if(loan.getLoanstatus().equals(status) && loan.getSanctionletter().getStatus().equals("Accepted"))
				
			{
				
		
		return loan;
				
				
			}
	
	else {
		
		throw new LoanstatusNotSanctionedOrSanctionLetterNotAcceptedException("Loanstatus NotSanctioned OrSanctionLetter Not Accepted");
		
	     }
			
		}
		
     else
			
		{
			
			throw new CustomerNotFoundException("Customer Not Present");
			
		}
		
		
	}

	@Override
	public List<LoanApplication> getAllCustomerSanctionedData() {
		
      List<LoanApplication>list=rr.findAll();
		
		
		return list;
	}

	@Override
	public List<LoanApplication> getAcceptedData(String status) {
		
		
		
		List<LoanApplication>loan=rr.findBySanctionletter_Status(status);
		
		return loan;
	}

	
}
		
	

	
	
