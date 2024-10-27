package com.app.servicei;

import java.util.List;

import com.app.model.Cibil;
import com.app.model.Enquiry;

public interface ServiceI {

public 	Enquiry updatecibildata(Enquiry enq);

public List<Enquiry> getAllEnquires();

public List<Enquiry> findByenquirystatus(String enquirystatus);

}
