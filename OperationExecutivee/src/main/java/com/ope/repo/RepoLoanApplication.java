package com.ope.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ope.model.LoanApplication;
@Repository
public interface RepoLoanApplication extends JpaRepository<LoanApplication, Integer> {

}
