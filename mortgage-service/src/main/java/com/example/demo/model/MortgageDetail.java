package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Mortgage;

import lombok.Data;

@Data
public class MortgageDetail {
	
	private String status;

	private String instanceId;

	private Integer instancePort;

	private List<Mortgage> mortgages= new ArrayList<>();

}
