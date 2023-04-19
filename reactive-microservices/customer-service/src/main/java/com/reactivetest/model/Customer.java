package com.reactivetest.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Customer {
	
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private int age;
	@Transient
	private List<Account> accounts;

}
