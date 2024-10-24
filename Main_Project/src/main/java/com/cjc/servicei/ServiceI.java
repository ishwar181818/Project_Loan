package com.cjc.servicei;

import java.util.List;

import com.cjc.model.Enquiry;

public interface ServiceI {

	public Enquiry saveData(Enquiry enq);

	public List<Enquiry> getallEnquiries();

	public void deleteSingleCustomer(int cid);

	public Enquiry updateData(Enquiry enq, int cid);

	public Enquiry getSingleData(int cid);

	public void deleteAllData();

	

}
