package com.example.demo.model;

import java.util.List;

import com.example.demo.entity.Loan;

import lombok.Data;

@Data
public class CustomerLoanDetails {
	
	private String instanceId; private Integer instancePort;

	private List<Loan> loans;

	private MortgageDetail mortgageDetail;

}
