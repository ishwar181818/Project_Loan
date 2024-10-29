package com.cjc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.exception.MailidInvalidException;
import com.cjc.exception.MobileNoInvalidException;
import com.cjc.model.Enquiry;
import com.cjc.repo.Repo;
import com.cjc.servicei.ServiceI;
@Service
public class ServiceImpl implements ServiceI {
	
	String enquirystatus="pending";
	
	@Autowired
	Repo rr;

	@Override
	public Enquiry saveData(Enquiry enq) {
		
		enq.setEnquirystatus(enquirystatus);
		long digit =0;
		
    for(long i = enq.getMobileno() ; i > 0 ;i= i/10)
		
	    {
		
		
			digit++;
			
		}
		
		if(digit== 10)
			
		{
			System.out.println("Mobile No is Valid");
			
		}
		
		else
			
		{
			throw new MobileNoInvalidException("Mobile No is Invalid");
			
			
		}
		
		if(enq.getEmail().contains("@gmail.com"))
			
		{
			
			System.out.println("Email is valid");
		}
		
		else
			
		{
			
			throw new MailidInvalidException("Email is Invalid");
			
		}
		
		Enquiry e=rr.save(enq);
		
		
		return e;
	}

	@Override
	public List<Enquiry> getallEnquiries() {
		
		
	List<Enquiry>l	=rr.findAll();
		return l
				;
	}

	@Override
	public void deleteSingleCustomer(int cid) {
		
		
		rr.deleteById(cid);
		
		
		
		
	}

	@Override
	public Enquiry updateData(Enquiry enq, int cid) {
		
	Optional<Enquiry>op=	rr.findById(cid);
	
	if(op.isPresent())
		
	{
		
		Enquiry enq1=op.get();
		
		//enq1.setFirstname(enq.getFirstname());
		
		//enq1.setLastname(enq.getLastname());
		
		//enq1.setAge(enq.getAge());
		
		enq1.setEmail(enq.getEmail());
		
		enq1.setMobileno(enq.getMobileno());
		
		//enq1.setPancard(enq.getPancard());
		
		rr.save(enq1);
		
		return enq1;
		
		
		
	}
	
	else {
		
		return null;
		
		
	}
	
		
		
		
	}

	@Override
	public Enquiry getSingleData(int cid) {
		
		
		Optional<Enquiry>op=  rr.findById(cid);
		
		if(op.isPresent())
			
		{
			
			Enquiry enq=op.get();
			
			
			return enq;
			
		}
		
		
		else {
			
			
			return null;
			
			
		}
		
		
		
		
		
		
	}

	@Override
	public void deleteAllData() {
		
		rr.deleteAll();
		
		
	}
	
	
	

	
	
	
	

}
