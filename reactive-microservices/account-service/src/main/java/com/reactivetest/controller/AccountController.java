package com.reactivetest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactivetest.model.Account;
import com.reactivetest.repository.AccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountRepository accountRepository;

	@GetMapping("/customer/{customerId}")
	public Flux<Account> findByCustomer(@PathVariable String customerId){
		LOGGER.info("findByCustomer: customerId={}", customerId);
		return accountRepository.findByCustomerId(customerId);
	}

	@GetMapping("/findAll")
	public Flux<Account> findAll(){
		LOGGER.info("findAll");
		return accountRepository.findAll();
	}

	@GetMapping("/{id}")
	public Mono<Account> findById(@PathVariable String id){
		LOGGER.info("findAll");
		return accountRepository.findById(id);
	}

	@PostMapping("/save")
	public Mono<Account> create(@RequestBody Account account) {
		LOGGER.info("create: {}", account);
		return accountRepository.save(account);
	}




}
