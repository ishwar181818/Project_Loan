package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.LoanApplication;
@Repository
public interface RepoLoanApplication extends JpaRepository<LoanApplication, Integer>{

}
