package com.cjc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.model.Enquiry;
import com.cjc.repo.Repo;
import com.cjc.servicei.ServiceI;
@Service
public class ServiceImpl implements ServiceI {
	@Autowired
	Repo rr;

	@Override
	public Enquiry saveData(Enquiry enq) {
		
		
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
