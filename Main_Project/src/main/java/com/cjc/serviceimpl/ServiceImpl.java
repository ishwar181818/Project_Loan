package com.cjc.serviceimpl;

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

	
	
	
	

}
