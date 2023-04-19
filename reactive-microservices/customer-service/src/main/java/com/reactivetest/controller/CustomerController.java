package com.reactivetest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.reactivetest.model.Account;
import com.reactivetest.model.Customer;
import com.reactivetest.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerRepository repository;
	
	@Autowired
    private WebClient.Builder webClientBuilder;
	
	@GetMapping("/{id}")
	public Mono<Customer> findById(@PathVariable("id") String id) {
		LOGGER.info("findById: id={}", id);
		return repository.findById(id);
	}

	@GetMapping("/")
	public Flux<Customer> findAll() {
		LOGGER.info("findAll");
		return repository.findAll();
	}
	
	@PostMapping("/")
	public Mono<Customer> create(@RequestBody Customer customer) {
		LOGGER.info("create: {}", customer);
		return repository.save(customer);
	}
	
	@GetMapping("/{id}/accounts")
	public Flux<Account> findByIdWithAccounts(@PathVariable("id") String id) {
		Flux<Account> accountList = webClientBuilder.build().get().uri("http://localhost:4444/account/customer/{customerId}", id)
				.retrieve().bodyToFlux(Account.class).log();
		return accountList;
	}
	
	
	

}
