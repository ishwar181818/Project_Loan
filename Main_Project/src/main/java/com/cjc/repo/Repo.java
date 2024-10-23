package com.cjc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.model.Enquiry;
@Repository
public interface Repo  extends JpaRepository<Enquiry, Integer>{

	
}
