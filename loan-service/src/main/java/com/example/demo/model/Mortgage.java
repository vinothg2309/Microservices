package com.example.demo.model;

import lombok.Data;

@Data
public class Mortgage {
	
	private Integer mortgageId;

	private Integer customerNumber;

	private String address; private String worth;

	private String loan; private String emi;

	private String tenure;

}
