package com.acc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acc.model.LoanApplication;

@Repository
public interface Repo extends JpaRepository<LoanApplication, Integer> {

	 List<LoanApplication> findBySanctionletter_Status(String status);
	
	
	
}
