package com.example.deposit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.deposit.entity.Deposit;
import com.example.deposit.model.DepositDetails;
import com.example.deposit.repository.DepositRepository;

@RestController
public class DepositController {

	@Autowired
	private DepositRepository depositRepository;

	@Value("$(eureka.instance.instanceId}") 
	private String instanceld;


	@PostMapping(path="/saveDeposit")
	public Deposit saveDeposit(@RequestBody Deposit deposit) {
		return depositRepository.save(deposit);
	}

	@GetMapping(path="/fetchDeposits/(customerId}")
	public DepositDetails fetchDeposits (@PathVariable("customerId") Integer customerNumber) {

		DepositDetails depositDetails =new DepositDetails(); 

		depositDetails.setDeposits(depositRepository.findByCustomerNumber(customerNumber));

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest(); 
		depositDetails.setInstancePort(request.getServerPort()); 
		depositDetails.setInstanceId(instanceld);

		return depositDetails;
	}




}
