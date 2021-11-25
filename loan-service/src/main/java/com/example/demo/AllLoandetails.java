package com.example.demo;

import java.util.List;

import com.example.demo.entity.Loan;
import com.example.demo.model.MortgageDetail;

import lombok.Data;

@Data
public class AllLoandetails {
	
	private List<Loan> loanDetails;
	
	private String instanceId;
	
	private Integer instancePort;
	
	private MortgageDetail mortgageDetail;
}
