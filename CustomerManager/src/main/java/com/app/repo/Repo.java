package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Enquiry;
@Repository
public interface Repo extends JpaRepository<Enquiry,Integer> {
	
	
	public List<Enquiry> findByEnquirystatus(String enquirystatus);

}
