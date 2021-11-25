package com.example.deposit.model;

import java.util.List;

import com.example.deposit.entity.Deposit;

import lombok.Data;

@Data
public class DepositDetails {
	
	private String instanceId;

	private Integer instancePort;

	private List<Deposit> deposits;

}
