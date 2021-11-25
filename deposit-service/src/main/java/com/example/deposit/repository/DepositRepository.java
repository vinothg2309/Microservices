package com.example.deposit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.deposit.entity.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer>{
	

	public List<Deposit> findByCustomerNumber(Integer customerNumber);


}
