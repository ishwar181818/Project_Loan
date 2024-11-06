package com.man.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.man.model.LoanApplication;
@Repository
public interface LoanApplicationRepo extends JpaRepository<LoanApplication, Integer> {

}
