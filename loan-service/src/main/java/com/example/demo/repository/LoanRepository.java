package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer>{

	List<Loan> findByCustomerNumber(Integer customerId);
	
}
