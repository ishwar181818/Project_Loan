package com.cjc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cjc.count.EnquiryCountDto;
import com.cjc.model.Enquiry;
@Repository
public interface Repo  extends JpaRepository<Enquiry, Integer>{
	
	Enquiry findByUsernameAndPassword(String username, String password);
	
	 @Query("SELECT new com.cjc.count.EnquiryCountDto( " +
	           "COUNT(CASE WHEN e.enquirystatus = 'pending' THEN 1 END), " +
	           "COUNT(CASE WHEN e.enquirystatus = 'Approved' THEN 1 END), " +
	           "COUNT(CASE WHEN e.enquirystatus = 'Rejected' THEN 1 END)) " +
	           "FROM Enquiry e")
	    EnquiryCountDto getEnquiryStatusCounts();

	
}
