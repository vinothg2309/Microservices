package com.example.demo.entity;

import lombok.Data;


import javax.persistence. Entity; import javax.persistence. GeneratedValue;

import javax.persistence.GenerationType; import javax.persistence. Id; import javax.persistence. Table;

@Entity

@Table(name="LOAN")
@Data
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer loanId;

	private Integer customerNumber;

	private String type; private String amount;

	private String tenure;

	private String interest;

}
