package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="MORTGAGE") 
@Data
public class Mortgage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer mortgageId;

	private Integer customerNumber;

	private String address;

	private String worth; 
	private String loan;

	private String emi; 
	private String tenure;

}